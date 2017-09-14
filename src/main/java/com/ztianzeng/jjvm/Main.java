package com.ztianzeng.jjvm;

import org.junit.Test;

import java.io.File;
import java.io.UnsupportedEncodingException;

import static com.ztianzeng.jjvm.ByteClassLoader.*;
import static com.ztianzeng.jjvm.ConstantPool.*;

/**
 * @author : 赵天增
 * @create : 2017-09-13 14:11
 * 描述 ：
 */
public class Main {
    @Test
    public void test() throws Exception {
        File file = new File("target\\test-classes\\HelloWorld.class");//此时文件已经清空
        decodeByByte(getByte(file));
    }

    public static Class decodeByByte(byte[] c) throws UnsupportedEncodingException {
        Class aClass = new Class();
        aClass.setMagic(readMagic(c));
        aClass.setMinorVersion(readU2(c));
        aClass.setMajorVersion(readU2(c));
        aClass.setConstantPool(readConstantPool(c));
        // 在解析的时候如果是使用16进制的数据，则直接将属性定义为String，然后使用原始的读取数据的方式
        aClass.setAccessFlags(readByte(c, 2));

        short thisClassIndex = readU2(c);
        aClass.setThisClass(aClass.getConstantPool()[thisClassIndex]);

        short superClassIndex = readU2(c);
        aClass.setSuperClass(aClass.getConstantPool()[superClassIndex]);


        return aClass;
    }
}
