package com.ztianzeng.jjvm.attribute;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author : 赵天增
 * @create : 2017-09-16 10:47
 * 描述 ：
 */
public class LineNumber {
    private int start_pc;    // Program Counter (PC) corresponds to line
    private int line_number; // number in source file

    public LineNumber(DataInputStream file) throws IOException {
        this(file.readUnsignedShort(), file.readUnsignedShort());
    }

    public LineNumber(int start_pc, int line_number) {
        this.start_pc = start_pc;
        this.line_number = line_number;
    }
}
