package com.ztianzeng.jjvm;

import com.ztianzeng.jjvm.instructions.load.Aload;

import static com.ztianzeng.jjvm.ByteClassLoader.readU1;

/**
 * @author : 赵天增
 * @create : 2017-09-14 14:54
 * 描述 ：Code 标识实现当前方法的Java虚拟机字节码,记录emmmm进栈出栈操作
 */
public class Code {
    private Code[] code;

    public Code(int codeLength) {
        this.code = new Code[codeLength];
    }

    /**
     * 添加操作码
     */
    public Code[] getInstructions(byte[] c) {
        for (int i = 0;i < this.code.length;i++){
            byte index = readU1(c);
            switch (index) {
                case 0x19:
                    break;
                case 0x2a:
                    code[i] = new Aload(0);
                    break;
            }
        }
        return code;
    }
}
