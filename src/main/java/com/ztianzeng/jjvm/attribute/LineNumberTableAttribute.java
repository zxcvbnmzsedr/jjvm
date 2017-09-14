package com.ztianzeng.jjvm.attribute;

/**
 * @author : 赵天增
 * @create : 2017-09-14 15:00
 * 描述 ：
 */
public class LineNumberTableAttribute extends AttributeInfo{
    public LineNumberTableAttribute(short attributeNameIndex, int attributeLength) {
        super(attributeNameIndex, attributeLength);
    }
}
