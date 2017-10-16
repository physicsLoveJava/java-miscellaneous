package com.lujian.tech.libs.nio;

import com.lujian.commons.TimeUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class ChannelToChannel {

    public static void main(String[] args) {

        TimeUtils.logMethodDuration(new TimeUtils.MethodAction() {
            @Override
            public void run() {
                try (FileInputStream fis = new FileInputStream("e://1.mp4");
                     FileOutputStream fos = new FileOutputStream("e://2.mp4")) {
                    FileChannel fisChannel = fis.getChannel();
                    FileChannel fosChannel = fos.getChannel();
                    fisChannel.transferTo(0, fisChannel.size(), fosChannel);
                    fisChannel.close();
                    fosChannel.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }

}
