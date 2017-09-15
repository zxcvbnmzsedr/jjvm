package com.ztianzeng.jjvm;

/**
 * @author : 赵天增
 * @create : 2017-09-15 9:57
 * 描述 ：虚拟机类，因为执行只需要关心两件事
 * 1） 属性 2）方法，所以在只需要定义这两个就可以了
 */
public interface JvmClass {
    /**
     * 获取方法
     *
     * @param name        方法名
     * @param descriptor  方法描述
     * @param accessFlags 方法访问操作符
     *
     * @return
     */
    MethodInfo getMethod(String name, String descriptor, int accessFlags);


}
