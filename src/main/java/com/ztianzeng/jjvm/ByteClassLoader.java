package com.ztianzeng.jjvm;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author : 赵天增
 * @create : 2017-09-13 10:53
 * 描述 ：将字节码解析出来加载到class中
 */
public class ByteClassLoader {


    public static byte readU1(byte[] c) {
        return Byte.parseByte(readByte(c, 1), 16);
    }

    /**
     * 这尼玛不按照常理来，只能重新写一个了
     * @param c
     * @return
     */
    public static short readInstruction(byte[] c) {
        return Short.parseShort(readByte(c, 1), 16);
    }
    /**
     * 读取两个字节的无符号数
     *
     * @param c 类的字节码
     * @return
     */
    public static short readU2(byte[] c) {
        return Short.parseShort(readByte(c, 2), 16);
    }

    /**
     * 读取四个字节
     *
     * @param c
     * @return
     */
    public static int readU4(byte[] c) {
        return Integer.parseInt(readByte(c, 4), 16);
    }

    /**
     * 用于读取类最前面的四个字节
     *
     * @param c
     * @return
     */
    public static String readMagic(byte[] c) {
        return readByte(c, 4);
    }


    /**
     * 读取字节
     */
    public static String readByte(byte[] c, int n) {
        StringBuilder h = new StringBuilder();
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
