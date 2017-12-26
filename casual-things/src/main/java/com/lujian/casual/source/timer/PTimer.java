package com.lujian.casual.source.timer;

import java.util.PriorityQueue;

public class PTimer {

    private PriorityQueue<PTask> queue;

    private PThread pThread;

    public PTimer() {
        this.queue = new PriorityQueue<>();
        this.pThread = new PThread(queue);
    }

    public void start() {
        pThread.start();
    }

    public void addTask(PTask task, long delay) {
        task.time = delay + System.currentTimeMillis();
        queue.add(task);
    }

    public abstract static class PTask implements Comparable<PTask>, Runnable{

        protected long time;
        protected int period;

        @Override
        public int compareTo(PTask o) {
            return (int) (time - o.getTime());
        }

        public long getTime() {
            return time;
        }

    }

    private class PThread extends Thread{

        private PriorityQueue<PTask> queue;

        public PThread(PriorityQueue<PTask> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try{
                mainLoop();
            }catch (Exception e) {

            }
        }

        private void mainLoop() {
            try {
                while(true) {
                    synchronized (queue) {
                        if(queue.isEmpty()) {
                            break;
                        }
                        PTask task = queue.poll();
                        long cur = System.currentTimeMillis();
                        if(cur < task.getTime()) {
                            queue.wait(task.getTime() - cur);
                        }
                        task.run();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
