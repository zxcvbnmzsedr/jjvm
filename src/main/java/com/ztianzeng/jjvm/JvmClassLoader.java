package com.ztianzeng.jjvm;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author : 赵天增
 * @create : 2017-09-15 9:46
 * 描述 ：类加载器
 */
public class JvmClassLoader {

    /**
     * 类搜索路径
     */
    private Path path;

    public JvmClassLoader(Path path) {
        this.path = path;
    }

    public JvmClass loadClass(String className){
        String fileName = path + "/" + className.replace(".","/")+".class";
        Path path = Paths.get(fileName);
        // 如果文件存在加载文件的字节码
//        if (Files.exists(path)){
//            return JvmOpcodeClass.read(path);
//        }else {
//            return null;
//        }
        return null;
    }
}
