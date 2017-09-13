package com.ztianzeng.jjvm;

/**
 * @author : 赵天增
 * @create : 2017-09-13 14:20
 * 描述 ：常量池信息
 */
public class ConstantInfo {

    private static final short CONSTANT_Class = 7;
    private static final short CONSTANT_Fieldref = 9;
    private static final short CONSTANT_Methodref = 10;
    private static final short CONSTANT_InterfaceMethodref = 11;
    private static final short CONSTANT_String = 8;
    private static final short CONSTANT_Integer = 3;
    private static final short CONSTANT_Float = 4;
    private static final short CONSTANT_Long = 5;
    private static final short CONSTANT_Double = 6;
    private static final short CONSTANT_NameAndType = 12;
    private static final short CONSTANT_Utf8 = 1;
    private static final short CONSTANT_MethodHandle = 15;
    private static final short CONSTANT_MethodType = 16;
    private static final short CONSTANT_InvokeDynamic = 18;

    /**
     * 创建常量池里面的具体信息
     *
     * @return
     */
    public static ConstantInfo newConstantInfo(byte tag) {
        switch (tag) {
            case CONSTANT_Integer:
                return new ConstantIntegerInfo();
            case CONSTANT_Float:
                return new ConstantFloatInfo();
            case CONSTANT_Long:
                return new ConstantLongInfo();
            case CONSTANT_Double:
                return new ConstantDoubleInfo();
            default:
                return null;
        }
    }

    static class ConstantIntegerInfo extends ConstantInfo {
        int val;
    }

    static class ConstantFloatInfo extends ConstantInfo {
        float val;
    }

    static class ConstantLongInfo extends ConstantInfo {
        long val;
    }

    static class ConstantDoubleInfo extends ConstantInfo {
        double val;
    }


}
