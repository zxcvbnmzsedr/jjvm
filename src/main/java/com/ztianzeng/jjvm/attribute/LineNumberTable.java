package com.ztianzeng.jjvm.attribute;

import com.ztianzeng.jjvm.Constants;
import com.ztianzeng.jjvm.classfile.ConstantPool;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author : 赵天增
 * @create : 2017-09-14 15:00
 * 描述 ：
 */
public class LineNumberTable extends Attribute {
    private int line_number_table_length;
    private LineNumber[] line_number_table;

    public LineNumberTable(int name_index, int length,
                           LineNumber[] line_number_table,
                           ConstantPool constant_pool) {
        super(Constants.ATTR_LINE_NUMBER_TABLE, name_index, length, constant_pool);
        setLineNumberTable(line_number_table);
    }

    LineNumberTable(int name_index, int length, DataInputStream file,
                    ConstantPool constant_pool) throws IOException {
        this(name_index, length, (LineNumber[]) null, constant_pool);
        line_number_table_length = (file.readUnsignedShort());
        line_number_table = new LineNumber[line_number_table_length];

        for (int i = 0; i < line_number_table_length; i++)
            line_number_table[i] = new LineNumber(file);
    }

    public final void setLineNumberTable(LineNumber[] line_number_table) {
        this.line_number_table = line_number_table;

        line_number_table_length = (line_number_table == null) ? 0 :
                line_number_table.length;
    }

}
