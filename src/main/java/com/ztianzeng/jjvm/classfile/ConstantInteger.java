package com.ztianzeng.jjvm.classfile;

import sun.security.pkcs11.wrapper.Constants;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author : 赵天增
 * @create : 2017-09-15 15:37
 * 描述 ：
 */
public class ConstantInteger extends Constant {
    private int bytes;


    ConstantInteger(DataInputStream file) throws IOException {
        this(file.readInt());
    }
    ConstantInteger(int bytes) {
        super(com.ztianzeng.jjvm.Constants.CONSTANT_Integer);
        this.bytes = bytes;
    }


    public int getBytes() {
        return bytes;
    }
}
