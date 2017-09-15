package com.ztianzeng.jjvm.attribute;

import com.ztianzeng.jjvm.Constants;
import com.ztianzeng.jjvm.classfile.ConstantPool;
import com.ztianzeng.jjvm.classfile.ConstantUtf8;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author : 赵天增
 * @create : 2017-09-15 16:25
 * 描述 ：属性
 */
public abstract class Attribute {
    protected int name_index;
    protected int length;
    protected byte tag;
    protected ConstantPool constant_pool;

    protected Attribute(byte tag, int name_index, int length,
                        ConstantPool constant_pool) {
        this.tag = tag;
        this.name_index = name_index;
        this.length = length;
        this.constant_pool = constant_pool;
    }

    private static HashMap readers = new HashMap();

    public static void addAttributeReader(String name, AttributeReader r) {
        readers.put(name, r);
    }

    public static void removeAttributeReader(String name) {
        readers.remove(name);
    }

    public static final Attribute readAttribute(DataInputStream file, ConstantPool constant_pool) throws IOException {
        ConstantUtf8 c;
        String name;
        int name_index;
        int length;
        byte tag = Constants.ATTR_UNKNOWN;

        name_index = (int) file.readUnsignedShort();
        c = (ConstantUtf8) constant_pool.getConstant(name_index, Constants.CONSTANT_Utf8);
        name = c.getBytes();

        // Length of data in bytes
        length = file.readInt();

        // Compare strings to find known attribute
        for (byte i = 0; i < Constants.KNOWN_ATTRIBUTES; i++) {
            if (name.equals(Constants.ATTRIBUTE_NAMES[i])) {
                tag = i; // found!
                break;
            }

        }
        switch (tag) {
//            case Constants.ATTR_UNKNOWN:
//                AttributeReader r = (AttributeReader) readers.get(name);
//                if (r != null)
//                    return r.createAttribute(name_index, length, file, constant_pool);
//                else
//                    return new Unknown(name_index, length, file, constant_pool);
//            case Constants.ATTR_CONSTANT_VALUE:
//                return new ConstantValue(name_index, length, file, constant_pool);

            case Constants.ATTR_SOURCE_FILE:
                return new SourceFile(name_index, length, file, constant_pool);

            case Constants.ATTR_CODE:
                return new Code(name_index, length, file, constant_pool);

//            case Constants.ATTR_EXCEPTIONS:
//                return new ExceptionTable(name_index, length, file, constant_pool);
//
            case Constants.ATTR_LINE_NUMBER_TABLE:
                return new LineNumberTable(name_index, length, file, constant_pool);

            case Constants.ATTR_LOCAL_VARIABLE_TABLE:
                return new LocalVariableTable(name_index, length, file, constant_pool);
//
//            case Constants.ATTR_LOCAL_VARIABLE_TYPE_TABLE:
//                return new LocalVariableTypeTable(name_index, length, file, constant_pool);
//
//            case Constants.ATTR_INNER_CLASSES:
//                return new InnerClasses(name_index, length, file, constant_pool);
//
//            case Constants.ATTR_SYNTHETIC:
//                return new Synthetic(name_index, length, file, constant_pool);
//
//            case Constants.ATTR_DEPRECATED:
//                return new Deprecated(name_index, length, file, constant_pool);
//
//            case Constants.ATTR_PMG:
//                return new PMGClass(name_index, length, file, constant_pool);
//
//            case Constants.ATTR_SIGNATURE:
//                return new Signature(name_index, length, file, constant_pool);
//
//            case Constants.ATTR_STACK_MAP:
//                return new StackMap(name_index, length, file, constant_pool);

            default: // Never reached
                throw new IllegalStateException("Ooops! default case reached.");
        }
    }


    public final int getLength() {
        return length;
    }

    public final void setLength(int length) {
        this.length = length;
    }

    public final void setNameIndex(int name_index) {
        this.name_index = name_index;
    }


    public final int getNameIndex() {
        return name_index;
    }

    public final byte getTag() {
        return tag;
    }

    public final ConstantPool getConstantPool() {
        return constant_pool;
    }

    public final void setConstantPool(ConstantPool constant_pool) {
        this.constant_pool = constant_pool;
    }
}