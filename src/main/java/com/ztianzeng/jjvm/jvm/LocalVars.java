package com.ztianzeng.jjvm.jvm;

/**
 * 局部变量表
 */
public class LocalVars {
    Slots[] slots;

    public LocalVars(int maxLocals) {
        this.slots = new Slots[maxLocals];
    }


}
