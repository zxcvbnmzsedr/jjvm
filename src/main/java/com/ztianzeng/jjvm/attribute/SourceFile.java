package com.ztianzeng.jjvm.attribute;

import com.sun.org.apache.bcel.internal.Constants;
import com.ztianzeng.jjvm.classfile.ConstantPool;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author : 赵天增
 * @create : 2017-09-14 14:31
 * 描述 ：
 */
public class SourceFile extends Attribute {
    /**
     * 常量池索引
     */
    int sourcefileIndex;


    protected SourceFile(int name_index, int length, int sourcefile_index, ConstantPool constant_pool) {
        super(Constants.ATTR_SOURCE_FILE, name_index, length, constant_pool);
        this.sourcefileIndex = sourcefile_index;
    }

    SourceFile(int name_index, int length, DataInputStream file, ConstantPool constant_pool) throws IOException {
        this(name_index, length, file.readUnsignedShort(), constant_pool);
    }
}
