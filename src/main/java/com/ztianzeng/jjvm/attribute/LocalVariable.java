package com.ztianzeng.jjvm.attribute;

import com.ztianzeng.jjvm.classfile.ConstantPool;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author : 赵天增
 * @create : 2017-09-16 10:52
 * 描述 ：
 */
public class LocalVariable {
    private int start_pc;
    private int length;
    private int name_index;
    private int signature_index;
    private int index;
    private ConstantPool constant_pool;
    LocalVariable(DataInputStream file, ConstantPool constant_pool)
            throws IOException
    {
        this(file.readUnsignedShort(), file.readUnsignedShort(),
                file.readUnsignedShort(), file.readUnsignedShort(),
                file.readUnsignedShort(), constant_pool);
    }
    public LocalVariable(int start_pc, int length, int name_index,
                         int signature_index, int index,
                         ConstantPool constant_pool)
    {
        this.start_pc        = start_pc;
        this.length          = length;
        this.name_index      = name_index;
        this.signature_index = signature_index;
        this.index           = index;
        this.constant_pool   = constant_pool;
    }
}
