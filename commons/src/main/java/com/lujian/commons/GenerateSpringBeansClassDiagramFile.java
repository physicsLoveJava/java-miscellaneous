package com.lujian.commons;

import static com.lujian.commons.GenerateClassDiagramFile.createClassDiagram;

public class GenerateSpringBeansClassDiagramFile {

    public static void main(String[] args) {

//        createClassDiagram("E:\\frameworks\\java\\blitz4j\\src\\main",
//                "commons/src/main/resources/blitz4j.txt");

//        createClassDiagram("E:\\frameworks\\java\\conductor",
//                "commons/src/main/resources/conductor.txt");

        createClassDiagram("E:\\codes\\source code from github\\spring-framework\\spring-beans\\src\\main\\java\\org\\springframework\\beans",
                "commons/src/main/resources/spring-beans.txt");

    }

}
