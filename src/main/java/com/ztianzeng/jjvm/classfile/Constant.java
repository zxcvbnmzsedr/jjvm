package com.ztianzeng.jjvm.classfile;

import com.sun.org.apache.bcel.internal.classfile.ClassFormatException;
import com.ztianzeng.jjvm.Constants;

import java.io.DataInputStream;
import java.io.IOException;


/**
 * @author : 赵天增
 * @create : 2017-09-13 14:20
 * 描述 ：常量池信息
 */
public abstract class Constant {
    protected byte tag;

    public Constant(byte tag) {
        this.tag = tag;
    }

    public byte getTag() {
        return tag;
    }

    /**
     * 创建常量池里面的具体信息
     *
     * @return
     */
    final static Constant readConstant(DataInputStream file) throws IOException {
        byte tag = file.readByte();
        switch (tag) {
            case Constants.CONSTANT_Integer:
                return new ConstantInteger(file);
            case Constants.CONSTANT_Float:
                return new ConstantFloat(file);
            case Constants.CONSTANT_Long:
                return new ConstantLong(file);
            case Constants.CONSTANT_Double:
                return new ConstantDouble(file);
            case Constants.CONSTANT_Methodref:
                return new ConstantMethodref(file);
            case Constants.CONSTANT_Fieldref:
                return new ConstantFieldref(file);
            case Constants.CONSTANT_InterfaceMethodref:
                return new ConstantInterfaceMethodref(file);
            case Constants.CONSTANT_Class:
                return new ConstantClass(file);
            case Constants.CONSTANT_String:
                return new ConstantString(file);
            case Constants.CONSTANT_NameAndType:
                return new ConstantNameAndType(file);
            case Constants.CONSTANT_Utf8:
                return new ConstantUtf8(file);
            default:
                throw new ClassFormatException("Invalid byte tag in constant pool: " + tag);
        }
    }




}
