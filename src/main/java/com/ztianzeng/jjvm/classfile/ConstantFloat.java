package com.ztianzeng.jjvm.classfile;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author : 赵天增
 * @create : 2017-09-15 15:41
 * 描述 ：
 */
public class ConstantFloat extends Constant {
    private float bytes;

    ConstantFloat(float bytes) {
        super(Constants.CONSTANT_Float);
        this.bytes = bytes;
    }

    ConstantFloat(DataInputStream file) throws IOException {
        this(file.readFloat());
    }

    public float getBytes() {
        return bytes;
    }

}
