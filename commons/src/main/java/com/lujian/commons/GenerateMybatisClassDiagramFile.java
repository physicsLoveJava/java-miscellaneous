package com.lujian.commons;

import static com.lujian.commons.GenerateClassDiagramFile.createClassDiagram;

public class GenerateMybatisClassDiagramFile {

    public static void main(String[] args) {

        createClassDiagram("E:\\frameworks\\java\\mybatis-3\\src\\main\\java\\org\\apache\\ibatis",
                "commons/src/main/resources/ibatis.txt");

    }

    private static void generatePart(String part) {
        createClassDiagram("E:\\frameworks\\java\\guava\\guava\\src\\com\\google\\common\\" + part,
                "commons/src/main/resources/guava-" + part + ".txt");
    }

}
