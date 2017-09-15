package com.ztianzeng.jjvm.classfile;

import com.sun.org.apache.bcel.internal.Constants;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author : 赵天增
 * @create : 2017-09-15 16:11
 * 描述 ：
 */
public class ConstantInterfaceMethodref extends ConstantCP {
    ConstantInterfaceMethodref(DataInputStream file) throws IOException {
        super(Constants.CONSTANT_InterfaceMethodref, file);
    }

    public ConstantInterfaceMethodref(int class_index, int name_and_type_index) {
        super(Constants.CONSTANT_InterfaceMethodref, class_index, name_and_type_index);
    }
}
