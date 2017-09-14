package com.ztianzeng.jjvm.attribute;

/**
 * @author : 赵天增
 * @create : 2017-09-14 15:01
 * 描述 ：确定方法在执行过程中局部变量的信息
 */
public class LocalVariableTable extends AttributeInfo {

    public LocalVariableTable(short attributeNameIndex, int attributeLength) {
        super(attributeNameIndex, attributeLength);
    }
}
