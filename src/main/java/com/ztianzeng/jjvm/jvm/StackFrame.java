package com.ztianzeng.jjvm.jvm;

import com.ztianzeng.jjvm.classfile.ConstantPool;

/**
 * @author : 赵天增
 * @create : 2017-09-13 10:38
 * 描述 ：栈帧是用来存储数据和部分过程结果的数据结构
 */
public class StackFrame {
    /**
     * 常量池（Constant Pool）
     */
    private ConstantPool constantPool;
    /**
     * 局部变量表,就是以Slot组成的数据
     */
    private Slots<Object> localVariables;
    /**
     * 操作数栈
     */
    private SlotsStack<Object> operandStack;

    public StackFrame(ConstantPool constantPool, int variables, int stackSize) {
        this.constantPool = constantPool;
        this.operandStack = new SlotsStack(stackSize);
        this.localVariables = new Slots(variables);
    }
    /**
     * 动态链接
     */

    /**
     * 方法正常调用完成
     */

    /**
     * 方法异常调用完成
     */

    public Slots<Object> getLocalVariables() {
        return localVariables;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public SlotsStack<Object> getOperandStack() {
        return operandStack;
    }
}
