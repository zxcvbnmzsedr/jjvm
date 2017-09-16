package com.ztianzeng.jjvm;

import org.junit.Test;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Paths;

/**
 * @author : 赵天增
 * @create : 2017-09-13 14:11
 * 描述 ：
 */
public class Main {

    public static void main(String[] args){

        if(args.length == 0){
            System.out.println("usage: JJvm class [args...]");
            return;
        }
        VirtualMachine machine = new VirtualMachine(Paths.get("."),args[0]);

    }

    @Test
    public void test() throws Exception {
        File file = new File("target\\test-classes\\HelloWorld.class");
        JavaClass jca = new ClassParser(new DataInputStream(new FileInputStream(file)),"HelloWorld").parse();
        System.out.println(jca);
//        decodeByByte(getByte(file));
    }
//
//    public static Class decodeByByte(byte[] c) throws UnsupportedEncodingException {
//
//    }
}
