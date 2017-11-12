package com.lujian.tech.asm;

import org.apache.commons.io.FileUtils;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.IOException;

public class SourceCodeClassNodeTest {

    public static void main(String[] args) {

        String path = "tech-asm/src/main/resources/DsrdwxxServiceImpl.class";
        try {
            ClassNodeAdapter cn = new ClassNodeAdapter();
            ClassReader cr = new ClassReader(FileUtils.readFileToByteArray(new File(path)));
            cr.accept(cn, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
