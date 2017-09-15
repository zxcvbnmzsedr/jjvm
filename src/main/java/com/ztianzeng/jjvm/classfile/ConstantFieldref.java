package com.ztianzeng.jjvm.classfile;


import com.ztianzeng.jjvm.Constants;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author : 赵天增
 * @create : 2017-09-15 15:57
 * 描述 ：
 */
public class ConstantFieldref extends ConstantCP {

    ConstantFieldref(DataInputStream file) throws IOException{
        super(Constants.CONSTANT_Fieldref, file);
    }

    /**
     * @param class_index Reference to the class containing the Field
     * @param name_and_type_index and the Field signature
     */
    public ConstantFieldref(int class_index,
                            int name_and_type_index) {
        super(Constants.CONSTANT_Fieldref, class_index, name_and_type_index);
    }

}
