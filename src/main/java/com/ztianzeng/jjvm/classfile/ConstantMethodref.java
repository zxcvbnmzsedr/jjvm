package com.ztianzeng.jjvm.classfile;

import com.sun.org.apache.bcel.internal.Constants;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author : 赵天增
 * @create : 2017-09-15 15:53
 * 描述 ：
 */
class ConstantMethodref extends ConstantCP{

    ConstantMethodref(byte tag, int classIndex, int nameAndTypeIndex) {
        super(tag, classIndex, nameAndTypeIndex);
    }


    ConstantMethodref(DataInputStream file) throws IOException {
        super(Constants.CONSTANT_Methodref, file);
    }
    ConstantMethodref(int class_index,
                             int name_and_type_index) {
        super(Constants.CONSTANT_Methodref, class_index, name_and_type_index);
    }


}
