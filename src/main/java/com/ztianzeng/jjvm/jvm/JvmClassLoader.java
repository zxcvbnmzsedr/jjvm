package com.ztianzeng.jjvm.jvm;

import com.ztianzeng.jjvm.classfile.ClassParser;
import com.ztianzeng.jjvm.classfile.JavaClass;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;

public class JvmClassLoader {
    private Path classPath;

    public JvmClassLoader(Path path) {
        this.classPath = path;
    }

    public JavaClass loadClass(String initClass) throws IOException {
        return new ClassParser(new DataInputStream(new FileInputStream(classPath.toFile())), initClass).parse();
    }
}
