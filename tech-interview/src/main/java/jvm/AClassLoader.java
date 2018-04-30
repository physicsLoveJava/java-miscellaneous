package jvm;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

public class AClassLoader extends ClassLoader {

    public AClassLoader() {
        super(null);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        name = "jvm.Person";
        try {
            FileInputStream fis = new FileInputStream("tech-interview/src/main/resources/jvm/Person.txt");
            FileChannel fc = fis.getChannel();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            WritableByteChannel wbc = Channels.newChannel(baos);
            ByteBuffer read = ByteBuffer.allocate(1024);
            while (fc.read(read) != -1) {
                read.flip();
                wbc.write(read);
                read.clear();
            }
            byte[] bytes = baos.toByteArray();
            return super.defineClass(name, bytes, 0, bytes.length, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
