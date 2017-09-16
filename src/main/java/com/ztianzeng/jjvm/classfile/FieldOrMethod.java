package com.ztianzeng.jjvm.classfile;

import com.sun.org.apache.bcel.internal.classfile.ClassFormatException;
import com.ztianzeng.jjvm.attribute.Attribute;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author : 赵天增
 * @create : 2017-09-16 15:42
 * 描述 ：
 */
public abstract class FieldOrMethod extends AccessFlags {
    protected int name_index;
    protected int signature_index;
    protected int attributes_count;
    protected Attribute[] attributes;
    protected ConstantPool constant_pool;

    protected FieldOrMethod(DataInputStream file, ConstantPool constant_pool)
            throws IOException, ClassFormatException {
        this(file.readUnsignedShort(), file.readUnsignedShort(),
                file.readUnsignedShort(), null, constant_pool);

        attributes_count = file.readUnsignedShort();
        attributes = new Attribute[attributes_count];
        for (int i = 0; i < attributes_count; i++)
            attributes[i] = Attribute.readAttribute(file, constant_pool);
    }

    protected FieldOrMethod(int access_flags, int name_index, int signature_index,
                            Attribute[] attributes, ConstantPool constant_pool) {
        this.access_flags = access_flags;
        this.name_index = name_index;
        this.signature_index = signature_index;
        this.constant_pool = constant_pool;
        setAttributes(attributes);
    }

    public final void setAttributes(Attribute[] attributes) {
        this.attributes = attributes;
        attributes_count = (attributes == null) ? 0 : attributes.length;
    }
    public final String getName() {
        ConstantUtf8  c;
        c = (ConstantUtf8)constant_pool.getConstant(name_index,
                Constants.CONSTANT_Utf8);
        return c.getBytes();
    }
    public final String getSignature() {
        ConstantUtf8  c;
        c = (ConstantUtf8)constant_pool.getConstant(signature_index,
                Constants.CONSTANT_Utf8);
        return c.getBytes();
    }
}
