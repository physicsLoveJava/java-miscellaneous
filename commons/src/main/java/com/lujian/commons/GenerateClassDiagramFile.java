package com.lujian.commons;

import com.sun.deploy.util.StringUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class GenerateClassDiagramFile {

    public static void main(String[] args) {

//        createClassDiagram("E:\\frameworks\\java\\apache-commons\\commons-pool2-2.4.3-src\\commons-pool2-2.4.3-src\\src\\main\\java\\org\\apache\\commons\\pool2",
//                "commons/src/main/resources/pool.txt");

//        createClassDiagram("E:\\frameworks\\java\\apache-commons\\commons-validator-1.6-src\\commons-validator-1.6-src\\src\\main\\java\\org\\apache\\commons\\validator",
//                "commons/src/main/resources/validator.txt");

//        createClassDiagram("E:\\frameworks\\java\\apache-commons\\commons-transaction-1.2-src\\commons-transaction-1.2-src\\src\\java\\org\\apache\\commons\\transaction",
//                "commons/src/main/resources/transaction.txt");

//        createClassDiagram("E:\\frameworks\\java\\apache-commons\\commons-proxy-1.0-src\\commons-proxy-1.0-src\\src\\main\\java\\org\\apache\\commons\\proxy",
//                "commons/src/main/resources/proxy.txt");

//        createClassDiagram("E:\\frameworks\\java\\apache-commons\\commons-vfs2-distribution-2.2-src\\commons-vfs-2.2\\commons-vfs2\\src\\main\\java\\org\\apache\\commons\\vfs2",
//                "commons/src/main/resources/vfs2.txt");

//        createClassDiagram("E:\\frameworks\\java\\apache-commons\\ognl\\src\\main\\java\\org\\apache\\commons\\ognl",
//                "commons/src/main/resources/ognl.txt");
//
//        createClassDiagram("E:\\frameworks\\java\\apache-commons\\commons-dbcp2-2.1.1-src\\commons-dbcp2-2.1.1-src\\src\\main\\java\\org\\apache\\commons\\dbcp2",
//                "commons/src/main/resources/dbcp2.txt");
//
//        createClassDiagram("E:\\frameworks\\java\\apache-commons\\commons-attributes-2.2-src\\commons-attributes-2.2\\api\\src\\java\\org\\apache\\commons\\attributes",
//                "commons/src/main/resources/attributes.txt");

        createClassDiagram("E:\\frameworks\\java\\apache-commons\\apache-tomcat-7.0.82-src\\apache-tomcat-7.0.82-src\\java",
                "commons/src/main/resources/tomcat.txt");

    }

    public static void createClassDiagram(String sourceDir, String destFileName) {
        if(sourceDir == null || destFileName == null) {
            throw new IllegalArgumentException();
        }
        File file = new File(sourceDir);
        final File destFile = new File(destFileName);
        if (!file.exists()) {
            throw new RuntimeException("未找到的文件:" + sourceDir);
        }
        if(!destFile.exists()) {
            try {
                boolean newFile = destFile.createNewFile();
                if(!newFile) {
                    throw new RuntimeException("创建新文件失败");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(file.isDirectory()) {
            try {
                Files.walkFileTree(file.toPath(), new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        addContent(file, destFile);
                        return FileVisitResult.CONTINUE;
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            addContent(file.toPath(), destFile);
        }
    }

    private static void addContent(Path path, File destFile) {
        if(!path.toString().endsWith(".java")) {
            return;
        }
        try {
            List<String> lines = Files.readAllLines(path, Charset.forName("UTF-8"));
            StringBuilder sb = new StringBuilder();
            boolean start = false;
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                if(line.startsWith("public")) {
                    start = true;
                    sb.append(trim(line));
                    if(line.endsWith("{")) {
                        break;
                    }
                }else if(line.endsWith("{") && start) {
                    sb.append(" ").append(trim(line));
                    break;
                }else if(line.startsWith("class")
                    || line.startsWith("abstract")
                    || line.startsWith("interface")) {
                    start = true;
                    sb.append(trim(line));
                    if(line.endsWith("{")) {
                        break;
                    }
                }else if(start) {
                    sb.append(" ").append(trim(line));
                }
            }
            String str = sb.toString();
            str = str.replaceAll("public ", "")
                    .replaceAll("final", "")
                    .replaceAll("<(\\S)*>", "")
                    .replaceAll("<(\\w|,|\\s)*>", "")
                    .replaceAll(" \\{", "")
                    .replaceAll("@interface", "annotation")
                    .replaceAll("\\{", "");
            appendToFile(destFile, str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void appendToFile(File destFile, String str) {
        try {
            BufferedWriter writer = Files.newBufferedWriter(destFile.toPath(), Charset.forName("UTF-8"), StandardOpenOption.APPEND);
            writer.write(str);
            writer.write("\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String trim(String str) {
        if(str == null) {
            return null;
        }
        return StringUtils.trimWhitespace(str);
    }

}
