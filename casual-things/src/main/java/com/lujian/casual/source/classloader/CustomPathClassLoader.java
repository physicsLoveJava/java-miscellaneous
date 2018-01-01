package com.lujian.casual.source.classloader;

import sun.net.www.ParseUtil;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class CustomPathClassLoader extends URLClassLoader {

    private String myPackageName = "com.lujian.casual.source.classloader";

    public CustomPathClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    @Override
    protected Class<?> findClass(final String name) throws ClassNotFoundException {
        Class<?> loadedClass = super.findLoadedClass(name);
        if(loadedClass != null) {
            return loadedClass;
        }
        if(!myPackageName.startsWith(name)) {
            return super.loadClass(name);
        }else {
            return findClass(name);
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        File file = new File("ClassLoaderDeep.class");
        String path = file.getCanonicalPath();
        new CustomPathClassLoader(new URL[]{ParseUtil.fileToEncodedURL(file)},
                getSystemClassLoader());
    }
}
