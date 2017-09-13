package com.ztianzeng.jjvm;

import java.util.List;

import static com.ztianzeng.jjvm.ByteClassLoader.readU1;
import static com.ztianzeng.jjvm.ByteClassLoader.readU2;
import static com.ztianzeng.jjvm.ConstantInfo.newConstantInfo;

/**
 * @author : 赵天增
 * @create : 2017-09-13 10:46
 * 描述 ：运行时常量池
 */
public class ConstantPool {
    /**
     * 读取常量池里面的内容
     */
    public static ConstantInfo[] readConstantPool(byte[] c) {
        // 读取常量池的长度
        short length = readU2(c);
        ConstantInfo[] constantInfos = new ConstantInfo[length];
        // 循环创建常量信息
        for (int i = 0; i < length; i++) {
            constantInfos[i] = readConstantInfo(c);
            if (constantInfos[i] instanceof ConstantInfo.ConstantLongInfo || constantInfos[i] instanceof ConstantInfo.ConstantDoubleInfo)
                i++;
        }
        return constantInfos;
    }

    private static ConstantInfo readConstantInfo(byte[] c) {
        byte tag = readU1(c);
        ConstantInfo info = newConstantInfo(tag,c);
        return info;
    }
}
