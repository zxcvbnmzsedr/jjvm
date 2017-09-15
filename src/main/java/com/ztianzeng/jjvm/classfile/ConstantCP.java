package com.ztianzeng.jjvm.classfile;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author : 赵天增
 * @create : 2017-09-15 15:51
 * 描述 ：像有些多默认参数的需要继承这个
 */
public abstract class ConstantCP extends Constant {
    protected int classIndex, nameAndTypeIndex;

    public ConstantCP(ConstantCP c) {
        this(c.getTag(), c.getClassIndex(), c.getNameAndTypeIndex());
    }
    ConstantCP(byte tag, DataInputStream file) throws IOException
    {
        this(tag, file.readUnsignedShort(), file.readUnsignedShort());
    }
    protected ConstantCP(byte tag, int classIndex,
                         int nameAndTypeIndex) {
        super(tag);
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    public int getClassIndex() {
        return classIndex;
    }

    public int getNameAndTypeIndex() {
        return nameAndTypeIndex;
    }
}
