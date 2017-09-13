package com.ztianzeng.jjvm;

import org.junit.Test;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.*;

/**
 * @author : 赵天增
 * @create : 2017-09-13 10:53
 * 描述 ：将字节码解析出来加载到class中
 */
public class ByteClassLoader {
    @Test
    public void test() throws Exception {
        File file = new File("target\\test-classes\\HelloWorld.class");//此时文件已经清空
        decodeByByte(getByte(file));
    }

    public static Class decodeByByte(byte[] c) throws UnsupportedEncodingException {
        Class aClass = new Class();
        aClass.setMagic(readMagic(c,4));
        aClass.setMinorVersion(Short.parseShort(readMagic(c,2),16));
        aClass.setMajorVersion(Short.parseShort(readMagic(c,2),16));
        aClass.setConstantPoolCount(readMagic(c,2));
        return aClass;
    }

    /**
     * 读取四个字节
     */
    public static String readMagic(byte[] c,int n) {
        StringBuilder h = new StringBuilder( );
        for (int i = 0; i < n; i++) {
            String temp = Integer.toHexString(c[i] & 0xFF);
            if (temp.length() == 1) {
                temp = "0" + temp;
            }
            h.append(temp);
        }
        System.arraycopy(c, n, c, 0, c.length - n - n);
        return h.toString();
    }

    public static byte[] getByte(File file) throws Exception {
        byte[] bytes = null;
        if (file != null) {
            InputStream is = new FileInputStream(file);
            int length = (int) file.length();
            if (length > Integer.MAX_VALUE)   //当文件的长度超过了int的最大值
            {
                System.out.println("this file is max ");
                return null;
            }
            bytes = new byte[length];
            int offset = 0;
            int numRead = 0;
            while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
                offset += numRead;
            }
            //如果得到的字节长度和file实际的长度不一致就可能出错了
            if (offset < bytes.length) {
                System.out.println("file length is error");
                return null;
            }
            is.close();
        }
        return bytes;
    }
}
