package com.ztianzeng.jjvm.jvm;

import com.ztianzeng.jjvm.classfile.ConstantPool;

/**
 * @author : 赵天增
 * @create : 2017-09-13 10:48
 * 描述 ：虚拟机栈，用于存储栈帧StackFrame
 */
public class Stack {
    /**
     * 虚拟机栈的最大的深度
     */
    private int maxSize;
    /**
     * 栈帧
     */
    private java.util.Stack<StackFrame> stackFrames = new java.util.Stack<>();


    public StackFrame newFrame(ConstantPool constantPool,int variables,int stackSize) {
        StackFrame frame = new StackFrame(constantPool,variables,stackSize);
        stackFrames.push(frame);
        return frame;
    }

    public void push(StackFrame stackFrame) {
        assert stackFrames.size() < maxSize;
        stackFrames.push(stackFrame);
    }

    public StackFrame pop() {
        assert stackFrames.size() == 0;
        return stackFrames.pop();
    }

    public StackFrame top() {
        assert stackFrames.size() == 0;
        return stackFrames.peek();
    }


}
