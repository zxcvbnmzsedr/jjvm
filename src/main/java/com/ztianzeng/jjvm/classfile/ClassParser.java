package com.ztianzeng.jjvm.classfile;

import com.sun.org.apache.bcel.internal.classfile.ClassFormatException;
import com.ztianzeng.jjvm.attribute.Attribute;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipFile;

/**
 * @author : 赵天增
 * @create : 2017-09-15 13:06
 * 描述 ：仿造bcel包中的ClassParser体会大佬们是怎么写代码的
 */
public class ClassParser {

    private DataInputStream file;

    private com.ztianzeng.jjvm.classfile.ConstantPool constantPool;

    private String fileName;

    private int minorVersion, majorVersion;

    private ZipFile zip;

    private boolean isZip;

    private int accessFlg;

    private static final int BUFSIZE = 8192;

    private int classNameIndex, superClassNameIndex;

    private int[] interfaces;

    private Field[] fields;

    private Method[] methods;

    private Attribute[] attributes;

    public ClassParser(InputStream file, String fileName) {
        this.fileName = fileName;

        String clazz = file.getClass().getName();

        isZip = clazz.startsWith("java.util.zip.") || clazz.startsWith("java.util.jar.");

        if (file instanceof DataInputStream)
            this.file = (DataInputStream) file;
        else
            this.file = new DataInputStream(new BufferedInputStream(file, BUFSIZE));

    }

    public JavaClass parse() throws IOException {
        readID();

        readVersion();

        readConstantPool();

        readClassInfo();

        readInterfaces();

        readFields();

        readMethods();

        readAttributes();

        file.close();

        if (zip != null)
            zip.close();

        return new JavaClass(classNameIndex, superClassNameIndex,
                fileName, majorVersion, minorVersion, accessFlg,
                constantPool, interfaces, fields,
                methods, attributes, isZip? JavaClass.ZIP : JavaClass.FILE);
    }

    private void readAttributes() throws IOException {
        int attributeCount = file.readUnsignedShort();
        this.attributes = new Attribute[attributeCount];
        for (int i = 0; i < attributeCount; i++) {
            attributes[i] = Attribute.readAttribute(file, constantPool);
        }
    }

    private void readMethods() throws IOException {
        int methodCount = file.readUnsignedShort();
        this.methods = new Method[methodCount];
        for (int i = 0; i < methodCount; i++) {
            this.methods[i] = new Method(file, constantPool);
        }
    }

    private void readFields() throws IOException {
        int fieldCount = file.readUnsignedShort();
        this.fields = new Field[fieldCount];
        for (int i = 0; i < fieldCount; i++) {
            this.fields[i] = new Field(file, constantPool);
        }
    }

    private void readInterfaces() throws IOException {
        int interfacesCount = file.readUnsignedShort();
        this.interfaces = new int[interfacesCount];
        for (int i = 0; i < interfacesCount; i++) {
            this.interfaces[i] = file.readUnsignedShort();
        }

    }

    private void readClassInfo() throws IOException {
        this.accessFlg = file.readUnsignedShort();
        if ((accessFlg & Constants.ACC_INTERFACE) != 0) {
            accessFlg |= Constants.ACC_ABSTRACT;
        }
        if (((accessFlg & Constants.ACC_ABSTRACT) != 0) &&
                ((accessFlg & Constants.ACC_FINAL) != 0))
            throw new ClassFormatException("Class can't be both final and abstract");
        this.classNameIndex = file.readUnsignedShort();
        this.superClassNameIndex = file.readUnsignedShort();
    }

    private void readConstantPool() throws IOException {
        this.constantPool = new ConstantPool(file);
    }

    /**
     * 读取类的主副版本
     */
    private void readVersion() throws IOException {
        this.minorVersion = file.readUnsignedShort();
        this.majorVersion = file.readUnsignedShort();
    }

    /**
     * 检查开头的魔数
     * 如果不是魔数直接抛出异常
     */
    private void readID() throws IOException {
        int magix = 0xCAFEBABE;
        if (file.readInt() != magix) {
            throw new ClassFormatException(fileName + " is not a Java .class file");
        }

    }
}
