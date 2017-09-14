package com.ztianzeng.jjvm;

import lombok.Data;


/**
 * @author : 赵天增
 * @create : 2017-09-13 10:54
 * 描述 ： Class结构
 */
@Data
public class Class {
    /**
     * 魔数----必须以cafebabe开头,不会被改变
     */
    private String magic;

    /**
     * 副版本号
     */
    private short minorVersion;

    /**
     * 主版本号
     */
    private short majorVersion;
    /**
     * 常量池实例
     */
    private ConstantInfo[] constantPool;
    /**
     * 访问权限
     */
    private String accessFlags;
    /**
     * 记录的是字符在常量池中的地址,类名
     */
    private ConstantInfo thisClass;

    /**
     * 记录的是字符在常量池中的地址,父类名
     */
    private ConstantInfo superClass;

    /**
     * 接口数量
     */
    private short interfacesCount;

    // TODO: 2017/9/13 这边缺个接口的实例

    /**
     * 字段数量
     */
    private short fieldsCount;

    // TODO: 2017/9/13 这边缺个字段的实例

    /**
     * 方法数量
     */
    private short methodsCount;

    private MethodInfo[] method;

    /**
     * 属性数量
     */
    private short attributesCount;

    // TODO: 2017/9/13 这边缺个属性实例


}
