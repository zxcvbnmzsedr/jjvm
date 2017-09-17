package com.ztianzeng.jjvm.classfile;

import com.sun.org.apache.bcel.internal.classfile.ClassFormatException;
import com.ztianzeng.jjvm.attribute.Attribute;
import com.ztianzeng.jjvm.attribute.Code;
import com.ztianzeng.jjvm.jvm.Env;
import com.ztianzeng.jjvm.jvm.Slots;
import com.ztianzeng.jjvm.jvm.StackFrame;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author : 赵天增
 * @create : 2017-09-16 15:46
 * 描述 ：
 */
public class Method extends FieldOrMethod{
    public Method(DataInputStream file, ConstantPool constant_pool)
            throws IOException, ClassFormatException
    {
        super(file, constant_pool);
    }
    Method(int access_flags, int name_index, int signature_index,
                  Attribute[] attributes, ConstantPool constant_pool)
    {
        super(access_flags, name_index, signature_index, attributes, constant_pool);
    }

    /**
     * 执行方法
     * @param env 虚拟机
     * @param thiz
     * @param args
     * @throws Exception
     */
    public void call(Env env, Object thiz, Object ...args) throws Exception {
//        每次方法调用都会产生一个栈帧,执行的时候创建一个栈帧并将栈帧推入栈中，置于栈顶
        StackFrame frame = env.getStack().newFrame(constant_pool,((Code)getAttributes()[0]).getMaxStack(),((Code)getAttributes()[0]).getMaxLocals());

        Slots<Object> locals = frame.getLocalVariables();
        System.out.println(locals);

    }
}
