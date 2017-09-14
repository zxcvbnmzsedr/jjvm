package com.ztianzeng.jjvm.attribute;

/**
 * @author : 赵天增
 * @create : 2017-09-14 14:31
 * 描述 ：
 */
public class SourceFileAttribute extends AttributeInfo {
    /**
     * 常量池索引
     */
    short sourcefileIndex;


    public SourceFileAttribute(short attributeNameIndex, int attributeLength, short sourcefileIndex) {
        super(attributeNameIndex, attributeLength);
        this.sourcefileIndex = sourcefileIndex;
    }
}
