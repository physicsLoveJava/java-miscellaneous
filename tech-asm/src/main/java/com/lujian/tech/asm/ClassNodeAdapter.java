package com.lujian.tech.asm;

import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.analysis.Analyzer;
import org.objectweb.asm.tree.analysis.BasicInterpreter;
import org.objectweb.asm.tree.analysis.BasicValue;
import org.objectweb.asm.tree.analysis.Frame;

public class ClassNodeAdapter extends ClassNode {

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

    @Override
    public void visitEnd() {
        Analyzer a =
                new Analyzer(new BasicInterpreter());
        Frame[] frames = a.getFrames();
        for (int i = 0; i < frames.length; ++i) {
            System.out.println(frames[i]);
        }
        super.visitEnd();
    }
}
