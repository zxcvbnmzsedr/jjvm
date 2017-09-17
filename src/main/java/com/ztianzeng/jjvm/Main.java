package com.ztianzeng.jjvm;

import com.sun.nio.zipfs.ZipPath;
import com.ztianzeng.jjvm.classfile.ClassParser;
import com.ztianzeng.jjvm.classfile.JavaClass;
import com.ztianzeng.jjvm.jvm.VirtualMachine;
import org.junit.Test;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author : 赵天增
 * @create : 2017-09-13 14:11
 * 描述 ：
 */
public class Main {

    public static void main(String[] args) throws IOException {
//        if(args.length == 0){
//            System.out.println("usage: JJvm class [args...]");
//            return;
//        }
//        Path path = new ZipPath("");
        VirtualMachine machine = new VirtualMachine(Paths.get("."),args[0]);
//        VirtualMachine machine = new VirtualMachine(Paths.get("."),args[0]);
        machine.run();
        
    }

    @Test
    public void test() throws Exception {
        File file = new File("target\\test-classes\\HelloWorld.class");
        JavaClass jca = new ClassParser(new DataInputStream(new FileInputStream(file)),"HelloWorld").parse();
//        System.out.println();
        jca.getMethodByName("main").call()
    }
}
