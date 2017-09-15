package com.ztianzeng.jjvm.attribute;

import com.ztianzeng.jjvm.classfile.ConstantPool;

/**
 * @author : 赵天增
 * @create : 2017-09-14 15:01
 * 描述 ：确定方法在执行过程中局部变量的信息
 */
public class LocalVariableTable extends Attribute {


    protected LocalVariableTable(byte tag, int name_index, int length, ConstantPool constant_pool) {
        super(tag, name_index, length, constant_pool);
    }
}
