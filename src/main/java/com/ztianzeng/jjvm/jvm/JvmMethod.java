package com.ztianzeng.jjvm.jvm;

/**
 * @author: 赵天增
 * @create : 2017/9/16
 * 描述： 方法执行接口
 */
public interface JvmMethod {
    /**
     * 执行对象或者类方法
     * 方法被调用时，会产生一个新的栈帧，并推入当前线程的栈
     * 方法执行结束后，栈帧被退出，同时期返回值被推入上一个栈帧（当前方法的调用者）的操作数栈
     *
     * @param env  方法所在的运行环境
     * @param thiz 需要执行的类
     * @param args 参数
     */
    void call(Env env, Object thiz, Object... args) throws Exception;
}
