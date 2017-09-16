package com.ztianzeng.jjvm.jvm;

import com.ztianzeng.jjvm.classfile.JavaClass;

import java.nio.file.Path;

public class JvmClassLoader {
    private Path classPath;

    public JvmClassLoader(Path path) {
        this.classPath = path;
    }

    public JavaClass loadClass(){

    }
}
