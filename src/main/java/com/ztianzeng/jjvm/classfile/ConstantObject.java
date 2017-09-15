package com.ztianzeng.jjvm.classfile;


/**
 * @author : 赵天增
 * @create : 2017-09-15 15:12
 * 描述 ：
 */
public interface ConstantObject {
    public abstract Object getConstantValue(ConstantPool cp);
}
