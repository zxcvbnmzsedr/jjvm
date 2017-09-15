package com.ztianzeng.jjvm;

import com.sun.org.apache.bcel.internal.classfile.JavaClass;
import com.sun.org.apache.bcel.internal.classfile.Method;
import com.sun.org.apache.bcel.internal.classfile.Code;

/**
 * @author : 赵天增
 * @create : 2017-09-15 10:25
 * 描述 ：字节码方法（区别于native方法）
 */
public class JvmOpcodeMethod implements JvmMethod {

    private JavaClass classFile;

    private Method method;

    private Code code;

    public JvmOpcodeMethod(JavaClass classFile, Method method) {
        this.classFile = classFile;
        this.method = method;

    }
}
