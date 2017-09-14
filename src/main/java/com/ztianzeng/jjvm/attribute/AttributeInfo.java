package com.ztianzeng.jjvm.attribute;

/**
 * @author : 赵天增
 * @create : 2017-09-14 14:14
 * 描述 ：属性
 */
public abstract class AttributeInfo {
    /**
     * 常量池索引, 必须是CONSTANT_Utf8_info格式，表示当前属性的名字
     */
    short attributeNameIndex;
    /**
     * 跟随其后的字节的长度
     */
    int attributeLength;

    public AttributeInfo(short attributeNameIndex, int attributeLength) {
        this.attributeNameIndex = attributeNameIndex;
        this.attributeLength = attributeLength;
    }
}
