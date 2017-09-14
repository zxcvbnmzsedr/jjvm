package com.ztianzeng.jjvm;

import com.ztianzeng.jjvm.instructions.load.Aload;

import static com.ztianzeng.jjvm.ByteClassLoader.readInstruction;

/**
 * @author : 赵天增
 * @create : 2017-09-14 14:54
 * 描述 ：Code 标识实现当前方法的Java虚拟机字节码,记录emmmm进栈出栈操作
 */
public class Code {
    private final static short aload_0 = 0x2a;
    private final static short _return = 0xb1;
    private final static short invokespecial = 0xb7;
    private Code[] code;

    public Code(int codeLength) {
        this.code = new Code[codeLength];
    }

    /**
     * 添加操作码
     */
    public Code[] getInstructions(byte[] c) {
        for (int i = 0; i < this.code.length; i++) {
            short index = readInstruction(c);
            switch (index) {
                case aload_0:
                    code[i] = new Aload(0);
                    break;

                case _return:

                    break;

                case invokespecial:

                    break;

            }
        }
        return code;
    }
}
