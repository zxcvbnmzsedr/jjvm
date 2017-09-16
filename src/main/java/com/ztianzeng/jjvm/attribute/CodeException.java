package com.ztianzeng.jjvm.attribute;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author : 赵天增
 * @create : 2017-09-15 18:45
 * 描述 ：运行抛的异常
 */
public class CodeException {
    private int start_pc;
    private int end_pc;
    private int handler_pc;
    private int catch_type;

    public CodeException(int start_pc, int end_pc, int handler_pc,
                         int catch_type) {
        this.start_pc = start_pc;
        this.end_pc = end_pc;
        this.handler_pc = handler_pc;
        this.catch_type = catch_type;
    }

    CodeException(DataInputStream file) throws IOException {
        this(file.readUnsignedShort(), file.readUnsignedShort(),
                file.readUnsignedShort(), file.readUnsignedShort());
    }
}
