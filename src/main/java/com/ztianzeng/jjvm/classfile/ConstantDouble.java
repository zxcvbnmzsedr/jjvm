package com.ztianzeng.jjvm.classfile;

import com.ztianzeng.jjvm.Constants;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author : 赵天增
 * @create : 2017-09-15 15:49
 * 描述 ：
 */
public class ConstantDouble extends Constant{
    private double bytes;
    ConstantDouble(double bytes) {
        super(Constants.CONSTANT_Double);
        this.bytes = bytes;
    }
    ConstantDouble(DataInputStream file) throws IOException {
        this(file.readDouble());
    }

    public double getBytes() {
        return bytes;
    }
}
