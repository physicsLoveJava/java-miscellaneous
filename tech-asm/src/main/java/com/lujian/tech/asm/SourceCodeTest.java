package com.lujian.tech.asm;

import org.apache.commons.io.FileUtils;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.IOException;

public class SourceCodeTest {

    public static void main(String[] args) {

        String path = "tech-asm/src/main/resources/DsrdwxxServiceImpl.class";
        try {
            ClassWriter cw = new ClassWriter(0);
            MyClassAdapter adapter = new MyClassAdapter(cw);
            ClassReader cr = new ClassReader(FileUtils.readFileToByteArray(new File(path)));
            cr.accept(adapter, 0);
            byte[] bytes = cw.toByteArray();
            System.out.println(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
