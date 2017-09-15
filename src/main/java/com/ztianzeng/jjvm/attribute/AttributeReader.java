package com.ztianzeng.jjvm.attribute;


import com.ztianzeng.jjvm.classfile.ConstantPool;

import java.io.DataInputStream;

/**
 * @author : 赵天增
 * @create : 2017-09-15 16:32
 * 描述 ：
 */
public interface AttributeReader {
    Attribute createAttribute(int name_index,
                                     int length,
                                     DataInputStream file,
                                     ConstantPool constant_pool);
}
