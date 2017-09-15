package com.ztianzeng.jjvm.classfile;

import com.sun.org.apache.bcel.internal.classfile.ClassFormatException;
import com.ztianzeng.jjvm.Constants;
import com.ztianzeng.jjvm.classfile.Constant;

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
        for (int i = 0; i < this.constantPoolCount; i++) {
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

}
