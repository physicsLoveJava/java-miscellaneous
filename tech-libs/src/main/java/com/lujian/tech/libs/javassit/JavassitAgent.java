package com.lujian.tech.libs.javassit;

import javassist.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class JavassitAgent {

    public static void main(String[] args) {

        ClassPool pool = new ClassPool(true);
        try {
            CtClass ctClass = pool.get("com.lujian.tech.libs.javassit.StubClass");
            CtMethod[] methods = ctClass.getDeclaredMethods("add");
            for (CtMethod method : methods) {
                System.out.println(method.getName());
                CtMethod originMethod = CtNewMethod.copy(method, method.getName() + "$trace", ctClass, null);
                ctClass.addMethod(originMethod);
                String src = "{" +
                        "long start = System.nanoTime();" +
                        "Object rs = " + method.getName() + "$trace($$);" +
                        "long end = System.nanoTime();" +
                        "System.out.println(end - start);" +
                        "return ($r)rs;" +
                        "}";
                method.setBody(src);
            }
            ctClass.writeFile("");
            ctClass.toClass();
            System.out.println(new StubClass().add(10, 5));
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
