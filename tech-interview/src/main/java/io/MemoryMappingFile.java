package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MemoryMappingFile {

    public static void main(String[] args) {
        try {
            File file = new File("tech-interview/src/main/resources/1.txt");
            FileInputStream fis = new FileInputStream("tech-interview/src/main/resources/1.txt");
            FileChannel fc = fis.getChannel();
            MappedByteBuffer buffer = fc.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            if(buffer.hasRemaining()) {
                byte[] bytes = new byte[1000];
                buffer.get(bytes, 0, (int) file.length());
                System.out.println(new String(bytes));
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
