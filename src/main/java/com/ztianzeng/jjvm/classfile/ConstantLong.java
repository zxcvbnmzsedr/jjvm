package com.ztianzeng.jjvm.classfile;

import com.ztianzeng.jjvm.Constants;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author : 赵天增
 * @create : 2017-09-15 15:47
 * 描述 ：
 */
public class ConstantLong extends Constant {
    private long bytes;

    ConstantLong(long bytes) {
        super(Constants.CONSTANT_Long);
        this.bytes = bytes;
    }

    ConstantLong(DataInputStream file) throws IOException {
        this(file.readLong());
    }

    public long getBytes() {
        return bytes;
    }
}
