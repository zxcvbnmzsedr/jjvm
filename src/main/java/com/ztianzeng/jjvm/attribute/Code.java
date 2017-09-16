package com.ztianzeng.jjvm.attribute;

import com.ztianzeng.jjvm.Constants;
import com.ztianzeng.jjvm.classfile.ConstantPool;

import java.io.DataInputStream;
import java.io.IOException;


/**
 * @author : 赵天增
 * @create : 2017-09-14 14:50
 * 描述 ：Code属性
 */
public class Code extends Attribute {
    /**
     * 操作栈允许的最大深度
     */
    private int maxStack;
    /**
     * 当前方法引用的局部变量的个数包括
     * 调用此方法时用于传递参数的局部变量
     * long 和 double 型的局部变量的最大索引是 max_locals-2，其它类型的局部变量的
     * 最大索引是 max_locals-1.
     */
    private int maxLocals;
    private int codeLength;
    private byte[] code;

    /**
     * 异常处理器的大小
     */
    private int exceptionTableLength;

    private CodeException[] exception_table;

    /**
     * 标识附加属性的attributes的数量
     */
    private int attributesCount;
    /**
     * 附加属性，在attribute包中
     */
    private Attribute[] attributes;

    Code(int name_index, int length, DataInputStream file, ConstantPool constant_pool) throws IOException {
        this(name_index, length, file.readUnsignedShort(), file.readUnsignedShort(), (byte[]) null, (CodeException[]) null, (Attribute[]) null, constant_pool);

        codeLength = file.readInt();
        code = new byte[codeLength];
        exceptionTableLength = file.readUnsignedShort();
        exception_table = new CodeException[exceptionTableLength];

        for (int i = 0; i < exceptionTableLength; i++)
            exception_table[i] = new CodeException(file);

    /* Read all attributes, currently `LineNumberTable' and
     * `LocalVariableTable'
     */
        attributesCount = file.readUnsignedShort();
        attributes = new Attribute[attributesCount];
        for (int i = 0; i < attributesCount; i++)
            attributes[i] = Attribute.readAttribute(file, constant_pool);

    /* Adjust length, because of setAttributes in this(), s.b.  length
     * is incorrect, because it didn't take the internal attributes
     * into account yet! Very subtle bug, fixed in 3.1.1.
     */
        this.length = length;

    }

    public Code(int name_index, int length, int max_stack, int max_locals, byte[] code, CodeException[] exception_table, Attribute[] attributes, ConstantPool constant_pool) {
        super(Constants.ATTR_CODE, name_index, length, constant_pool);
        this.maxStack = max_stack;
        this.maxLocals = max_locals;
        setCode(code);
        setExceptionTable(exception_table);
        setAttributes(attributes);
    }

    public final void setCode(byte[] code) {
        this.code = code;
        codeLength = (code == null) ? 0 : code.length;
    }

    public final void setExceptionTable(CodeException[] exception_table) {
        this.exception_table = exception_table;
        exceptionTableLength = (exception_table == null) ? 0 :
                exception_table.length;
    }

    public final void setAttributes(Attribute[] attributes) {
        this.attributes = attributes;
        attributesCount = (attributes == null) ? 0 : attributes.length;
        length = calculateLength(); // Adjust length
    }

    private final int calculateLength() {
        int len = 0;
        for (int i = 0; i < attributesCount; i++)
            len += attributes[i].length + 6 /*attribute header size*/;
        return len + getInternalLength();
    }

    private final int getInternalLength() {
        return 2 /*max_stack*/ + 2 /*max_locals*/ + 4 /*code length*/
                + codeLength /*byte-code*/
                + 2 /*exception-table length*/
                + 8 * exceptionTableLength /* exception table */
                + 2 /* attributes count */;
    }
}
