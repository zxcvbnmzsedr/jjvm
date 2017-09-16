package com.ztianzeng.jjvm.classfile;



import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author : 赵天增
 * @create : 2017-09-15 15:06
 * 描述 ：
 */
public class ConstantClass extends Constant implements ConstantObject{
    private int nameIndex;
    ConstantClass(DataInputStream file) throws IOException {
        this(file.readUnsignedShort());
    }

    ConstantClass(int nameIndex) {
        super(Constants.CONSTANT_Class);
        this.nameIndex = nameIndex;
    }

    @Override
    public Object getConstantValue(ConstantPool cp) {
        Constant c = cp.getConstant(nameIndex, Constants.CONSTANT_Utf8);
        return ((ConstantUtf8)c).getBytes();
    }
    public final int getNameIndex() { return nameIndex; }
}
