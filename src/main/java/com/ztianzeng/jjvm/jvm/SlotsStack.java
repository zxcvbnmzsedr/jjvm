package com.ztianzeng.jjvm.jvm;

/**
 * @author: 赵天增
 * @create : 2017/9/17
 * 描述：操作数栈
 */
public class SlotsStack<T> {
    private T[] buffer;

    private int end = 0;

    public SlotsStack(int size) {
        buffer = (T[]) new Object[size];
    }
}
