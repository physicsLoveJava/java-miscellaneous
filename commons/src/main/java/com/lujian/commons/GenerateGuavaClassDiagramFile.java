package com.lujian.commons;

import static com.lujian.commons.GenerateClassDiagramFile.createClassDiagram;

public class GenerateGuavaClassDiagramFile {

    public static void main(String[] args) {

//        createClassDiagram("E:\\frameworks\\java\\guava\\guava\\src\\com\\google\\common\\base",
//                "commons/src/main/resources/guava-base.txt");

//        generatePart("cache");
//        generatePart("collect");
//        generatePart("escape");
        generatePart("eventbus");
        generatePart("graph");
        generatePart("hash");
        generatePart("html");
        generatePart("io");
        generatePart("math");
        generatePart("net");
        generatePart("primitives");
        generatePart("reflect");
        generatePart("util");
        generatePart("xml");

    }

    private static void generatePart(String part) {
        createClassDiagram("E:\\frameworks\\java\\guava\\guava\\src\\com\\google\\common\\" + part,
                "commons/src/main/resources/guava-" + part + ".txt");
    }

}
