package com.ztianzeng.jjvm.jvm;

import com.ztianzeng.jjvm.jvm.Slot;

/**
 * 局部变量表
 */
public class LocalVars {
    Slot[] slots;

    public LocalVars(int maxLocals) {
        this.slots = new Slot[maxLocals];
    }

    public void setInt(int index, int val) {
        this.slots[index].num = val;
    }

    public int getInt(int index) {
        return this.slots[index].num;
    }

}
