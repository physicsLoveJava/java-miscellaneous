package com.lujian.casual.source.classloader;

public class ClassLoaderDeep {

    public static void main(String[] args) {

        try {
            ClassLoader loader = ClassLoader.getSystemClassLoader();
            loader.loadClass("com.lujian.casual.source.classloader.ClassLoaderDeep");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
