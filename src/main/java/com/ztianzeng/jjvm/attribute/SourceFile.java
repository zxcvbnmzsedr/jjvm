package com.ztianzeng.jjvm.attribute;

import com.ztianzeng.jjvm.classfile.ConstantPool;

/**
 * @author : 赵天增
 * @create : 2017-09-14 14:31
 * 描述 ：
 */
public class SourceFile extends Attribute {
    /**
     * 常量池索引
     */
    short sourcefileIndex;


    protected SourceFile(byte tag, int name_index, int length, ConstantPool constant_pool) {
        super(tag, name_index, length, constant_pool);
    }
}
