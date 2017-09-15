package com.ztianzeng.jjvm;

import com.sun.org.apache.bcel.internal.classfile.JavaClass;
import com.sun.org.apache.bcel.internal.classfile.Method;

import java.nio.file.Path;
import java.util.HashMap;

/**
 * @author : 赵天增
 * @create : 2017-09-15 10:04
 * 描述 ：
 */
public class JvmOpcodeClass implements JvmClass {

    private JavaClass classFile;

    private HashMap<String, JvmMethod> methods = new HashMap<>();
    @Override
    public MethodInfo getMethod(String name, String descriptor, int accessFlags) {
        return null;
    }

    public static JvmClass read(Path path) {
        return new JvmOpcodeClass();
    }

    public JvmOpcodeClass(JavaClass classFile) {
        this.classFile = classFile;
        for (Method method : classFile.getMethods()) {
            String name = method.getName();
            String desc = method.getSignature();
            methods.put(name+":"+desc, new JvmOpcodeMethod(classFile, method));
        }
    }

}
