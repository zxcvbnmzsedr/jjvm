package com.ztianzeng.jjvm;

import static com.ztianzeng.jjvm.ByteClassLoader.*;

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
    public static ConstantInfo newConstantInfo(byte tag, byte[] c) {
        switch (tag) {
            case CONSTANT_Integer:
                return new ConstantIntegerInfo(readU4(c));
            case CONSTANT_Float:
                return new ConstantFloatInfo(readU4(c));
            case CONSTANT_Long:
                return new ConstantLongInfo(readU4(c), readU4(c));
            case CONSTANT_Double:
                return new ConstantDoubleInfo(readU4(c), readU4(c));
            case CONSTANT_Methodref:
                return new ConstantMethodrefInfo(readU2(c), readU2(c));
            case CONSTANT_Fieldref:
                return new ConstantFieldrefInfo(readU2(c), readU2(c));
            case CONSTANT_InterfaceMethodref:
                return new ConstantInterfaceMethodrefInfo(readU2(c), readU2(c));
            case CONSTANT_Class:
                return new ConstantClassInfo(readU2(c));
            case CONSTANT_String:
                return new ConstantStringInfo(readU2(c));
            case CONSTANT_NameAndType:
                return new ConstantNameAndType(readU2(c), readU2(c));
            default:
                return null;
        }
    }

    static class ConstantIntegerInfo extends ConstantInfo {
        int val;

        ConstantIntegerInfo(int val) {
            this.val = val;
        }
    }

    static class ConstantFloatInfo extends ConstantInfo {
        float val;

        ConstantFloatInfo(float val) {
            // TODO: 2017/9/13 还需要进行计算
            this.val = val;
        }
    }

    static class ConstantLongInfo extends ConstantInfo {
        long val;

        ConstantLongInfo(byte high, byte low) {
            this.val = ((long) high << 32) + low;
        }
    }

    static class ConstantDoubleInfo extends ConstantInfo {
        double val;

        // TODO: 2017/9/13 还需要计算
        ConstantDoubleInfo(byte high, byte low) {
            this.val = (high << 32) + low;
        }
    }

    static class ConstantNameAndType extends ConstantInfo {
        short nameIndex;
        short descriptorIndex;

        public ConstantNameAndType(short nameIndex, short descriptorIndex) {
            this.nameIndex = nameIndex;
            this.descriptorIndex = descriptorIndex;
        }
    }

    static class ConstantStringInfo extends ConstantInfo {
        short stringIndex;

        ConstantStringInfo(short stringIndex) {
            this.stringIndex = stringIndex;
        }
    }


    static class ConstantClassInfo extends ConstantInfo {
        short nameIndex;

        ConstantClassInfo(short nameIndex) {
            this.nameIndex = nameIndex;
        }
    }


    static class ConstantMethodrefInfo extends ConstantInfo {
        short classIndex;
        short nameAndTypeIndex;

        ConstantMethodrefInfo(short classIndex, short nameAndTypeIndex) {
            this.classIndex = classIndex;
            this.nameAndTypeIndex = nameAndTypeIndex;
        }
    }


    static class ConstantFieldrefInfo extends ConstantInfo {
        short classIndex;
        short nameAndTypeIndex;

        ConstantFieldrefInfo(short classIndex, short nameAndTypeIndex) {
            this.classIndex = classIndex;
            this.nameAndTypeIndex = nameAndTypeIndex;
        }
    }


    static class ConstantInterfaceMethodrefInfo extends ConstantInfo {
        short classIndex;
        short nameAndTypeIndex;

        ConstantInterfaceMethodrefInfo(short classIndex, short nameAndTypeIndex) {
            this.classIndex = classIndex;
            this.nameAndTypeIndex = nameAndTypeIndex;
        }
    }


}
