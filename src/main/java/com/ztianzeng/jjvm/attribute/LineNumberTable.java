package com.ztianzeng.jjvm.attribute;

import com.ztianzeng.jjvm.classfile.ConstantPool;

/**
 * @author : 赵天增
 * @create : 2017-09-14 15:00
 * 描述 ：
 */
public class LineNumberTable extends Attribute{

    protected LineNumberTable(byte tag, int name_index, int length, ConstantPool constant_pool) {
        super(tag, name_index, length, constant_pool);
    }
}
