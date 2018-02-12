package com.lujian.commons;

import com.sun.deploy.util.StringUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class GenerateJedisClassDiagramFile {

    public static void main(String[] args) {

        createClassDiagram("E:\\codes\\frameworks\\java\\jedis\\src\\main\\java\\redis\\clients",
                "commons/src/main/resources/jedis.txt");

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
