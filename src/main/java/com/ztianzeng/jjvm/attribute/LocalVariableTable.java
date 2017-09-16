package com.ztianzeng.jjvm.attribute;

import com.ztianzeng.jjvm.Constants;
import com.ztianzeng.jjvm.classfile.ConstantPool;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author : 赵天增
 * @create : 2017-09-14 15:01
 * 描述 ：确定方法在执行过程中局部变量的信息
 */
public class LocalVariableTable extends Attribute {
    private int local_variable_table_length; // Table of local
    private LocalVariable[] local_variable_table;

    public LocalVariableTable(int name_index, int length, DataInputStream file, ConstantPool constant_pool) throws IOException {
        this(name_index, length, (LocalVariable[]) null, constant_pool);

        local_variable_table_length = (file.readUnsignedShort());
        local_variable_table = new LocalVariable[local_variable_table_length];

        for (int i = 0; i < local_variable_table_length; i++)
            local_variable_table[i] = new LocalVariable(file, constant_pool);

    }

    public LocalVariableTable(int name_index, int length,
                              LocalVariable[] local_variable_table,
                              ConstantPool constant_pool) {
        super(Constants.ATTR_LOCAL_VARIABLE_TABLE, name_index, length, constant_pool);
        setLocalVariableTable(local_variable_table);
    }

    public final void setLocalVariableTable(LocalVariable[] local_variable_table) {
        this.local_variable_table = local_variable_table;
        local_variable_table_length = (local_variable_table == null) ? 0 :
                local_variable_table.length;
    }
}
