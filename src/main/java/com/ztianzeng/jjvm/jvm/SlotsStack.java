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

    public void push(T entity) throws IllegalArgumentException{
        this.push(entity, 1);
    }

    public void push(T entity, int size) throws IllegalArgumentException{
        if(size <=0 || end+size>buffer.length){
            throw new IllegalArgumentException("invalid entity size "+size);
        }
        buffer[end] = entity;
        for(int i=1; i<size; i++){
            buffer[end+i] = null;
        }
        end += size;

    }
}
