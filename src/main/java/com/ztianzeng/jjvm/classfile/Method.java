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
public class Method extends FieldOrMethod {
    public Method(DataInputStream file, ConstantPool constant_pool)
            throws IOException, ClassFormatException {
        super(file, constant_pool);
    }

    Method(int access_flags, int name_index, int signature_index,
           Attribute[] attributes, ConstantPool constant_pool) {
        super(access_flags, name_index, signature_index, attributes, constant_pool);
    }

    /**
     * 执行方法
     *
     * @param env  虚拟机
     * @param thiz
     * @param args
     * @throws Exception
     */
    public void call(Env env, Object thiz, Object... args) throws Exception {
//        每次方法调用都会产生一个栈帧,执行的时候创建一个栈帧并将栈帧推入栈中，置于栈顶
        StackFrame frame = env.getStack().newFrame(constant_pool, ((Code) getAttributes()[0]).getMaxStack(), ((Code) getAttributes()[0]).getMaxLocals());
        // Java 虚拟机使用局部变量表来完成方法调用时的参数传递，
        // 当一个方法被调用的时候，它的参数将会传递至从 0 开始的连续的局部变量表位置上。
        // 特别地，当一个实例方法被调用的时候， 第 0 个局部变量一定是用来存储被调用的实例方法所在的对象的引用(即 Java 语言中的“this”
        // 关键字)。后续的其他参数将会传递至从 1 开始的连续的局部变量表位置上。
        Slots<Object> locals = frame.getLocalVariables();
        int pos = 0;
        if (access_flags != Constants.ACC_STATIC) {
//            相当于java中的this，指向自己
            locals.set(0, thiz, 1);
            pos++;
        }

        for (Object arg : args) {
            locals.set(pos++, arg, 1);
        }

        // 设置完局部变量表之后执行字节码
        // 弹出最上面的栈帧
        StackFrame stackFrame = env.getStack().pop();
//        获取局部变量表
        Slots<Object> s = stackFrame.getLocalVariables();
//        获取自己
        JavaClass claz = (JavaClass) s.get(0);
        // 遍历方法
        for (Method method:claz.getMethods()){
            // 遍历属性
            for (Attribute attribute:method.getAttributes()){
                if (attribute instanceof Code){
//                    取出操作码
                    byte[] code = ((Code)attribute).getCode();
                    for(byte c:code){
                        switch (c){
//                            执行对应的操作码
                            case 0x00:
                                break;
                        }
                    }
                }
                System.out.println(attribute);
            }

        }


    }
}
