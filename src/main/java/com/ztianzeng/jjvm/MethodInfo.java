package com.ztianzeng.jjvm;

import com.ztianzeng.jjvm.attribute.AttributeInfo;

/**
 * @author : 赵天增
 * @create : 2017-09-14 14:11
 * 描述 ：方法定义
 */
public class MethodInfo {
    /**
     * 访问符
     */
    short accessFlags;
    /**
     * CONSTANT_Utf8_info结构,它要么表示初始化方法的名字（<init>或
     * <clinit>），要么表示一个方法的有效的非全限定名
     */
    short nameIndex;

    /**
     * CONSTANT_Utf8_info结构,标识一个有效的方法描述符
     */
    short descriptorIndex;
    /**
     * 标识附加属性的attributes的数量
     */
    short attributesCount;
    /**
     * 附加属性，在attribute包中
     */
    AttributeInfo attributes[];

    MethodInfo(short accessFlags, short nameIndex, short descriptorIndex, short attributesCount) {
        this.accessFlags = accessFlags;
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
        this.attributesCount = attributesCount;
        this.attributes = new AttributeInfo[attributesCount];
    }


    public static MethodInfo[] newMethodInfo(byte[] c, int length) {
        MethodInfo[] m = new MethodInfo[length];
//        for (int i = 0; i < length; i++) {
//            m[i] = new MethodInfo(readU2(c), readU2(c), readU2(c), readU2(c));
//            for (int j = 0; j < m[i].attributes.length; j++) {
//                m[i].attributes[j] = new CodeAttribute(readU2(c), readU4(c), c);
//            }
//        }
        return m;

    }
}
