package com.lujian.tech.libs.javassit;

import javassist.*;

public class JavassitDemo {

    public static void main(String[] args) {

        ClassPool cp = new ClassPool(true);
        try {
            cp.insertClassPath(new LoaderClassPath(JavassitDemo.class.getClassLoader()));
            CtClass ctClass = cp.get("com.lujian.tech.libs.javassit.StubClass");
            CtMethod method = ctClass.getDeclaredMethod("add");
            String src = "{" +
                    "int i = 100;" +
                    "System.out.println(i);" +
                    "System.out.println($args[0]);" +
                    "System.out.println($args[1]);" +
                    "System.out.println($class);" +
                    "System.out.println($type);" +
                    "return $1 - $2;" +
                    "}";
            method.setBody(src);
            Class targetClass = ctClass.toClass();
            StubClass targetStub = (StubClass) targetClass.newInstance();
            System.out.println(targetStub.add(10 , 5));
            System.out.println(new StubClass().add(10, 5));
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

}
