package com.ztianzeng.jjvm.classfile;

import com.sun.org.apache.bcel.internal.Constants;
import com.sun.org.apache.bcel.internal.classfile.ConstantPool;
import com.sun.org.apache.bcel.internal.classfile.ConstantUtf8;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author : 赵天增
 * @create : 2017-09-15 16:07
 * 描述 ：
 */
public class ConstantString extends Constant {
    private int stringIndex;

    ConstantString(DataInputStream file) throws IOException {
        this((int) file.readUnsignedShort());
    }

    public ConstantString(int string_index) {
        super(Constants.CONSTANT_String);
        this.stringIndex = string_index;
    }

    public int getStringIndex() {
        return stringIndex;
    }

    public void setStringIndex(int stringIndex) {
        this.stringIndex = stringIndex;
    }

    public Object getConstantValue(com.sun.org.apache.bcel.internal.classfile.ConstantPool cp) {
        com.sun.org.apache.bcel.internal.classfile.Constant c = cp.getConstant(stringIndex, Constants.CONSTANT_Utf8);
        return ((ConstantUtf8) c).getBytes();
    }

    public String getBytes(ConstantPool cp) {
        return (String) getConstantValue(cp);
    }
}
