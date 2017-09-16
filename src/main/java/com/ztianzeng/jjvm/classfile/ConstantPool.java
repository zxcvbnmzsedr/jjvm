package com.ztianzeng.jjvm.classfile;

import com.sun.org.apache.bcel.internal.classfile.ClassFormatException;

import java.io.DataInputStream;
import java.io.IOException;


/**
 * @author : 赵天增
 * @create : 2017-09-13 10:46
 * 描述 ：运行时常量池
 */
public class ConstantPool {
    /**
     * 常量池中常量的数量
     */
    private int constantPoolCount;

    private Constant[] constantPool;

    public ConstantPool(DataInputStream file) throws IOException {
        byte tag;
        this.constantPoolCount = file.readUnsignedShort();
        constantPool = new Constant[this.constantPoolCount];
        // 从1开始，0标识啥也不是来着
        for (int i = 1; i < this.constantPoolCount; i++) {
            this.constantPool[i] = Constant.readConstant(file);
            tag = constantPool[i].getTag();
            if ((tag == Constants.CONSTANT_Double) || (tag == Constants.CONSTANT_Long))
                i++;
        }
    }

    public Constant getConstant(int index, byte tag) {
        Constant c;
        c = getConstant(index);
        if (c == null)
            throw new ClassFormatException("Constant pool at index " + index + " is null.");

        if (c.getTag() == tag)
            return c;
        else
            throw new ClassFormatException("Expected class `" + Constants.CONSTANT_NAMES[tag] +
                    "' at index " + index + " and got " + c);
    }

    private Constant getConstant(int index) {
        if (index >= constantPool.length || index < 0) {
            throw new ClassFormatException("Invalid constant pool reference: " +
                    index + ". Constant pool size is: " +
                    constantPool.length);
        }
        return constantPool[index];
    }

    public String getConstantString(int index, byte tag)
            throws ClassFormatException {
        Constant c;
        int i;

        c = getConstant(index, tag);

    /* This switch() is not that elegant, since the two classes have the
     * same contents, they just differ in the name of the index
     * field variable.
     * But we want to stick to the JVM naming conventions closely though
     * we could have solved these more elegantly by using the same
     * variable name or by subclassing.
     */
        switch (tag) {
            case Constants.CONSTANT_Class:
                i = ((ConstantClass) c).getNameIndex();
                break;
            case Constants.CONSTANT_String:
                i = ((ConstantString) c).getStringIndex();
                break;
            default:
                throw new RuntimeException("getConstantString called with illegal tag " + tag);
        }

        // Finally get the string from the constant pool
        c = getConstant(i, Constants.CONSTANT_Utf8);
        return ((ConstantUtf8) c).getBytes();
    }
}
