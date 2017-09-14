package com.ztianzeng.jjvm.attribute;

import com.ztianzeng.jjvm.Code;

/**
 * @author : 赵天增
 * @create : 2017-09-14 14:50
 * 描述 ：Code属性
 */
public class CodeAttribute {
    /**
     * 操作栈允许的最大深度
     */
    short maxStack;

    /**
     * 当前方法引用的局部变量的个数包括
     * 调用此方法时用于传递参数的局部变量
     * long 和 double 型的局部变量的最大索引是 max_locals-2，其它类型的局部变量的
     * 最大索引是 max_locals-1.
     */
    short maxLocals;

    int codeLength;

    Code[] code;

    /**
     * 异常处理器的大小
     */
    short exceptionTableLength;

    // TODO: 2017/9/14 异常处理表

    /**
     * 标识附加属性的attributes的数量
     */
    short attributesCount;
    /**
     * 附加属性，在attribute包中
     */
    AttributeInfo attributes[];




}
