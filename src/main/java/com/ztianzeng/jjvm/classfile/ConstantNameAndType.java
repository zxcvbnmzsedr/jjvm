package com.ztianzeng.jjvm.classfile;

import com.sun.org.apache.bcel.internal.Constants;
import com.sun.org.apache.bcel.internal.classfile.ConstantPool;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author : 赵天增
 * @create : 2017-09-15 16:12
 * 描述 ：
 */
public class ConstantNameAndType extends Constant {
    private int name_index;      // Name of field/method
    private int signature_index; // and its signature

    ConstantNameAndType(DataInputStream file) throws IOException {
        this((int) file.readUnsignedShort(), (int) file.readUnsignedShort());
    }

    /**
     * @param name_index      Name of field/method
     * @param signature_index and its signature
     */
    public ConstantNameAndType(int name_index,
                               int signature_index) {
        super(Constants.CONSTANT_NameAndType);
        this.name_index = name_index;
        this.signature_index = signature_index;
    }

    public final int getNameIndex() {
        return name_index;
    }

    public final String getName(ConstantPool cp) {
        return cp.constantToString(getNameIndex(), Constants.CONSTANT_Utf8);
    }

    public final int getSignatureIndex() {
        return signature_index;
    }


    public final String getSignature(ConstantPool cp) {
        return cp.constantToString(getSignatureIndex(), Constants.CONSTANT_Utf8);
    }

    public final void setNameIndex(int name_index) {
        this.name_index = name_index;
    }

    public final void setSignatureIndex(int signature_index) {
        this.signature_index = signature_index;
    }

}
