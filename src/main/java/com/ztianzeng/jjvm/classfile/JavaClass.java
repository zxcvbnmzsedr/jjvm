/*
 * Copyright (c) 2007, 2017, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ztianzeng.jjvm.classfile;

/* ====================================================================
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2001 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "Apache" and "Apache Software Foundation" and
 *    "Apache BCEL" must not be used to endorse or promote products
 *    derived from this software without prior written permission. For
 *    written permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache",
 *    "Apache BCEL", nor may "Apache" appear in their name, without
 *    prior written permission of the Apache Software Foundation.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 */


import com.sun.org.apache.bcel.internal.classfile.Utility;
import com.sun.org.apache.bcel.internal.generic.Type;
import com.ztianzeng.jjvm.attribute.Attribute;
import com.ztianzeng.jjvm.attribute.SourceFile;
import com.ztianzeng.jjvm.classfile.*;

import java.util.StringTokenizer;

/**
 * Represents a Java class, i.e., the data structures, constant pool,
 * fields, methods and commands contained in a Java .class file.
 * See <a href="ftp://java.sun.com/docs/specs/">JVM
 * specification</a> for details.
 * <p>
 * The intent of this class is to represent a parsed or otherwise existing
 * class file.  Those interested in programatically generating classes
 * should see the <a href="../generic/ClassGen.html">ClassGen</a> class.
 *
 * @author <A HREF="mailto:markus.dahm@berlin.de">M. Dahm</A>
 * @version $Id: JavaClass.java,v 1.4 2007-07-19 04:34:42 ofung Exp $
 * @see com.sun.org.apache.bcel.internal.generic.ClassGen
 */
public class JavaClass extends AccessFlags {
    private String file_name;
    private String package_name;
    private String source_file_name = "<Unknown>";
    private int class_name_index;
    private int superclass_name_index;
    private String class_name;
    private String superclass_name;
    private int major, minor;  // Compiler version
    private ConstantPool constant_pool; // Constant pool
    private int[] interfaces;    // implemented interfaces
    private String[] interface_names;
    private Field[] fields;        // Fields, i.e., variables of class
    private Method[] methods;       // methods defined in the class
    private Attribute[] attributes;    // attributes defined in the class
    private byte source = HEAP; // Generated in memory

    public static final byte HEAP = 1;
    public static final byte FILE = 2;
    public static final byte ZIP = 3;

    static boolean debug = false; // Debugging on/off
    static char sep = '/';   // directory separator

    public JavaClass(int class_name_index,
                     int superclass_name_index,
                     String file_name,
                     int major,
                     int minor,
                     int access_flags,
                     ConstantPool constant_pool,
                     int[] interfaces,
                     Field[] fields,
                     Method[] methods,
                     Attribute[] attributes,
                     byte source) {
        if (interfaces == null) // Allowed for backward compatibility
            interfaces = new int[0];
        if (attributes == null)
            this.attributes = new Attribute[0];
        if (fields == null)
            fields = new Field[0];
        if (methods == null)
            methods = new Method[0];

        this.class_name_index = class_name_index;
        this.superclass_name_index = superclass_name_index;
        this.file_name = file_name;
        this.major = major;
        this.minor = minor;
        this.access_flags = access_flags;
        this.constant_pool = constant_pool;
        this.interfaces = interfaces;
        this.fields = fields;
        this.methods = methods;
        this.attributes = attributes;
        this.source = source;

        // Get source file name if available
        for (int i = 0; i < attributes.length; i++) {
            if (attributes[i] instanceof SourceFile) {
                source_file_name = ((SourceFile) attributes[i]).getSourceFileName();
                break;
            }
        }

    /* According to the specification the following entries must be of type
     * `ConstantClass' but we check that anyway via the
     * `ConstPool.getConstant' method.
     */
        class_name = constant_pool.getConstantString(class_name_index,
                Constants.CONSTANT_Class);
        class_name = Utility.compactClassName(class_name, false);

        int index = class_name.lastIndexOf('.');
        if (index < 0)
            package_name = "";
        else
            package_name = class_name.substring(0, index);

        if (superclass_name_index > 0) { // May be zero -> class is java.lang.Object
            superclass_name = constant_pool.getConstantString(superclass_name_index,
                    Constants.CONSTANT_Class);
            superclass_name = Utility.compactClassName(superclass_name, false);
        } else
            superclass_name = "java.lang.Object";

        interface_names = new String[interfaces.length];
        for (int i = 0; i < interfaces.length; i++) {
            String str = constant_pool.getConstantString(interfaces[i], Constants.CONSTANT_Class);
            interface_names[i] = Utility.compactClassName(str, false);
        }
    }

    /**
     * Constructor gets all contents as arguments.
     *
     * @param class_name_index      Class name
     * @param superclass_name_index Superclass name
     * @param file_name             File name
     * @param major                 Major compiler version
     * @param minor                 Minor compiler version
     * @param access_flags          Access rights defined by bit flags
     * @param constant_pool         Array of constants
     * @param interfaces            Implemented interfaces
     * @param fields                Class fields
     * @param methods               Class methods
     * @param attributes            Class attributes
     */
    public JavaClass(int class_name_index,
                     int superclass_name_index,
                     String file_name,
                     int major,
                     int minor,
                     int access_flags,
                     ConstantPool constant_pool,
                     int[] interfaces,
                     Field[] fields,
                     Method[] methods,
                     Attribute[] attributes) {
        this(class_name_index, superclass_name_index, file_name, major, minor, access_flags,
                constant_pool, interfaces, fields, methods, attributes, HEAP);
    }

    public Attribute[] getAttributes() {
        return attributes;
    }

    /**
     * @return Class name.
     */
    public String getClassName() {
        return class_name;
    }

    /**
     * @return Package name.
     */
    public String getPackageName() {
        return package_name;
    }

    /**
     * @return Class name index.
     */
    public int getClassNameIndex() {
        return class_name_index;
    }

    /**
     * @return Constant pool.
     */
    public ConstantPool getConstantPool() {
        return constant_pool;
    }

    /**
     * @return Fields, i.e., variables of the class. Like the JVM spec
     * mandates for the classfile format, these fields are those specific to
     * this class, and not those of the superclass or superinterfaces.
     */
    public Field[] getFields() {
        return fields;
    }

    /**
     * @return File name of class, aka SourceFile attribute value
     */
    public String getFileName() {
        return file_name;
    }

    /**
     * @return Names of implemented interfaces.
     */
    public String[] getInterfaceNames() {
        return interface_names;
    }

    /**
     * @return Indices in constant pool of implemented interfaces.
     */
    public int[] getInterfaceIndices() {
        return interfaces;
    }

    /**
     * @return Major number of class file version.
     */
    public int getMajor() {
        return major;
    }

    /**
     * @return Methods of the class.
     */
    public Method[] getMethods() {
        return methods;
    }

    /**
     * 根据名称获取方法，需要遍历常量池 ---不应该这样做，权宜之计
     * @return
     */
    public Method getMethodByName(String name) {
        Method[] methods = getMethods();
        for (Method method:methods){
            if(name.equals(method.getName())){
                return method;
            }
        }
        return null;
    }

    /**
     * @return Minor number of class file version.
     */
    public int getMinor() {
        return minor;
    }

    /**
     * @return sbsolute path to file where this class was read from
     */
    public String getSourceFileName() {
        return source_file_name;
    }

    /**
     * @return Superclass name.
     */
    public String getSuperclassName() {
        return superclass_name;
    }

    /**
     * @return Class name index.
     */
    public int getSuperclassNameIndex() {
        return superclass_name_index;
    }


    /**
     * @param attributes .
     */
    public void setAttributes(Attribute[] attributes) {
        this.attributes = attributes;
    }

    /**
     * @param class_name .
     */
    public void setClassName(String class_name) {
        this.class_name = class_name;
    }

    /**
     * @param class_name_index .
     */
    public void setClassNameIndex(int class_name_index) {
        this.class_name_index = class_name_index;
    }

    /**
     * @param constant_pool .
     */
    public void setConstantPool(ConstantPool constant_pool) {
        this.constant_pool = constant_pool;
    }

    /**
     * @param fields .
     */
    public void setFields(Field[] fields) {
        this.fields = fields;
    }

    /**
     * Set File name of class, aka SourceFile attribute value
     */
    public void setFileName(String file_name) {
        this.file_name = file_name;
    }

    /**
     * @param interface_names .
     */
    public void setInterfaceNames(String[] interface_names) {
        this.interface_names = interface_names;
    }

    /**
     * @param interfaces .
     */
    public void setInterfaces(int[] interfaces) {
        this.interfaces = interfaces;
    }

    /**
     * @param major .
     */
    public void setMajor(int major) {
        this.major = major;
    }

    /**
     * @param methods .
     */
    public void setMethods(Method[] methods) {
        this.methods = methods;
    }

    /**
     * @param minor .
     */
    public void setMinor(int minor) {
        this.minor = minor;
    }

    /**
     * Set absolute path to file this class was read from.
     */
    public void setSourceFileName(String source_file_name) {
        this.source_file_name = source_file_name;
    }

    /**
     * @param superclass_name .
     */
    public void setSuperclassName(String superclass_name) {
        this.superclass_name = superclass_name;
    }

    /**
     * @param superclass_name_index .
     */
    public void setSuperclassNameIndex(int superclass_name_index) {
        this.superclass_name_index = superclass_name_index;
    }

    private static final String indent(Object obj) {
        StringTokenizer tok = new StringTokenizer(obj.toString(), "\n");
        StringBuffer buf = new StringBuffer();

        while (tok.hasMoreTokens())
            buf.append("\t" + tok.nextToken() + "\n");

        return buf.toString();
    }


    public final boolean isSuper() {
        return (access_flags & Constants.ACC_SUPER) != 0;
    }

    public final boolean isClass() {
        return (access_flags & Constants.ACC_INTERFACE) == 0;
    }


}
