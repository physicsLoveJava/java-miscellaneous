package com.lujian.tech.libs.plantuml;

import net.sourceforge.plantuml.BlockUml;
import net.sourceforge.plantuml.SourceStringReader;

import java.io.IOException;
import java.util.List;

public class PlantumlUsage {

    public static void main(String[] args) throws IOException {
        String text = "@startuml\n" +
                "\n" +
                "component [Slf4j-api] as api\n" +
                "component [Slf4j-nop] as nop\n" +
                "component [Slf4j-simple] as simple\n" +
                "component [Slf4j-log4j12] as log4j12\n" +
                "component [Slf4j-jdk14] as jdk14\n" +
                "component [Slf4j-jcl] as jcl\n" +
                "\n" +
                "api --> StaticLoggerBinder\n" +
                "\n" +
                "StaticLoggerBinder --> nop\n" +
                "StaticLoggerBinder --> simple\n" +
                "StaticLoggerBinder --> log4j12\n" +
                "StaticLoggerBinder --> jdk14\n" +
                "StaticLoggerBinder --> jcl\n" +
                "\n" +
                "LoggerFactoryBinder <|-- StaticLoggerBinder\n" +
                "LoggerFactoryBinder -- getLoggerFactory\n" +
                "getLoggerFactory -- getLogger\n" +
                "\n" +
                "@enduml";
        SourceStringReader reader = new SourceStringReader(text);
        List<BlockUml> blockList = reader.getBlocks();
        for (BlockUml uml : blockList) {
            System.out.println(uml.getDiagram());
        }
//        reader.generateImage(System.out, new FileFormatOption(FileFormat.SVG));
    }

}
