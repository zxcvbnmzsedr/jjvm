package com.ztianzeng.jjvm.jvm;

/**
 * @author: 赵天增
 * @create : 2017/9/16
 * 描述： 运行环境
 */
public class Env {
    /**
     * 虚拟机栈
     */
    private Stack stack = new Stack();

    /**
     * 当前虚拟机
     */
    private VirtualMachine virtualMachine;

    public Env(VirtualMachine virtualMachine) {
        this.virtualMachine = virtualMachine;
    }

    public Stack getStack() {
        return stack;
    }

    public VirtualMachine getVirtualMachine() {
        return virtualMachine;
    }
}
