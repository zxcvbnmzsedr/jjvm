package com.ztianzeng.jjvm.classfile;

import com.ztianzeng.jjvm.Constants;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author : 赵天增
 * @create : 2017-09-15 15:18
 * 描述 ：
 */
public class ConstantUtf8 extends Constant {
    private String bytes;

    public ConstantUtf8(String bytes) {
        super(Constants.CONSTANT_Utf8);
        if (bytes == null) {
            throw new IllegalArgumentException("bytes must not be null!");
        }
        this.bytes = bytes;
    }

    public ConstantUtf8(DataInputStream file) throws IOException {
        super(Constants.CONSTANT_Utf8);
        this.bytes = file.readUTF();
    }

    public String getBytes() {
        return bytes;
    }

}
