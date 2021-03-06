package com.ztianzeng.jjvm.jvm;


import com.ztianzeng.jjvm.classfile.JavaClass;

import java.io.IOException;
import java.nio.file.Path;

/**
 * @author : 赵天增
 * @create : 2017-09-15 9:45
 * 描述 ：虚拟机
 */
public class VirtualMachine {
    /**
     * 包含main方法的类
     */
    private String initClass;
    /**
     * 类加载器
     */
    private JvmClassLoader classLoader;

    /**
     * 初始化虚拟机，需要寻找并解析类的信息
     *
     * @param path
     * @param initClass
     */
    public VirtualMachine(Path path, String initClass) {
        this.initClass = initClass;
        classLoader = new JvmClassLoader(path);
    }

    public void run() throws IOException {
        /**
         * 创建虚拟机环境
         */
        Env env = new Env(this);
        /**
         * 获取JavaClass
         */
        JavaClass javaClass = classLoader.loadClass(initClass);

        System.out.println(javaClass.getMethods());
    }
}
