package com.lujian.tech.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.ASM4;

public class MyClassAdapter extends ClassVisitor {

    public MyClassAdapter(ClassWriter cw) {
        super(ASM4, cw);
    }

    @Override
    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        System.out.println("access = [" + access + "], name = [" + name + "], desc = [" + desc + "], signature = [" + signature + "], value = [" + value + "]");
        return super.visitField(access, name, desc, signature, value);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        System.out.println("access = [" + access + "], name = [" + name + "], desc = [" + desc + "], signature = [" + signature + "], exceptions = [" + exceptions + "]");
        return super.visitMethod(access, name, desc, signature, exceptions);
    }
}
