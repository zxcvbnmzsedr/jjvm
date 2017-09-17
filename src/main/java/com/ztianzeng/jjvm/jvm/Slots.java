package com.ztianzeng.jjvm.jvm;

import java.util.NoSuchElementException;

/**
 * 栈帧中的局部变量表的容量以变量槽Slot为最小单位
 * 一个局部变量可以保存一个类型为 boolean、 byte、 char、 short、 float、 reference
 * 和returnAddress 的数据，两个局部变量可以保存一个类型为 long 和 double 的数据
 */
public class Slots<T> {
    private T[] buffer;

    public Slots(int size) {
        buffer = (T[]) new Object[size];
    }

    public void set(int pos, T entity, int size) throws IllegalArgumentException{
        if(pos <0 || pos+size > buffer.length){
            throw new IllegalArgumentException("invalid entity size "+size);
        }
        buffer[pos] = entity;
        for(int i=1; i<size; i++){
            buffer[pos+i] = null;
        }
    }

    public Object get(int pos) throws NoSuchElementException {
        if(pos<0 || pos >= buffer.length){
            throw new NoSuchElementException();
        }
        return buffer[pos];
    }

    public int size(){
        return buffer.length;
    }
}
