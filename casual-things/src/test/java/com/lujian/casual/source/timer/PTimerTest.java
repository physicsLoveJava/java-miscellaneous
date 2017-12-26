package com.lujian.casual.source.timer;

import org.junit.Test;

import java.util.Timer;
import java.util.TimerTask;

import static org.junit.Assert.*;

public class PTimerTest {
    @Test
    public void start() throws Exception {

        PTimer timer = new PTimer();
        timer.addTask(new PTimer.PTask() {
            @Override
            public void run() {
                System.out.println("run it");
            }
        }, 1000);

        timer.start();
        Thread.sleep(1000);
    }

    @Test
    public void startForRealTimer() throws InterruptedException {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("task");
            }
        }, 1000);
        Thread.sleep(1000);
    }
}