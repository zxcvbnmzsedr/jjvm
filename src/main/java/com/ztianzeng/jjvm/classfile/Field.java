package com.ztianzeng.jjvm.classfile;

import com.sun.org.apache.bcel.internal.classfile.ClassFormatException;
import com.ztianzeng.jjvm.attribute.Attribute;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author : 赵天增
 * @create : 2017-09-15 16:18
 * 描述 ：
 */
public class Field extends FieldOrMethod {
    public Field(DataInputStream file, ConstantPool constant_pool)
            throws IOException, ClassFormatException {
        super(file, constant_pool);
    }

    public Field(int access_flags, int name_index, int signature_index,
                 Attribute[] attributes, ConstantPool constant_pool) {
        super(access_flags, name_index, signature_index, attributes, constant_pool);
    }

}
