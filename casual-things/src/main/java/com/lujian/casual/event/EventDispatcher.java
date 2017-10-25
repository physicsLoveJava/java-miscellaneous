package com.lujian.casual.event;

import java.util.ArrayDeque;
import java.util.Queue;

public class EventDispatcher extends Thread{

    private Queue<EventTarget> queue;

    private final Object lock = new Object();

    private EventDispatcher() {
        setName("Event Dispatcher Thread");
        queue = new ArrayDeque<>();
        start();
    }

    public void dispatch(Element element, EventType type, EventAction action) {
        EventTarget target = new EventTarget(element, type, action);
        queue.add(target);
        synchronized (lock) {
            lock.notify();
        }
    }

    static class EventDispatcherHolder {
        static EventDispatcher dispatcher = new EventDispatcher();
    }

    public static EventDispatcher get() {
        return EventDispatcherHolder.dispatcher;
    }

    @Override
    public void run() {
        synchronized (lock) {
            while (true) {
                while (!queue.isEmpty()) {
                    EventTarget target = queue.poll();
                    target.action.perform(target);
                }
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class EventTarget {

        private Element e;
        private EventType type;
        private EventAction action;

        public EventTarget(Element e, EventType type, EventAction action) {
            this.e = e;
            this.type = type;
            this.action = action;
        }

        public String getElementName() {
            return e.getName();
        }

        public EventType getType() {
            return type;
        }
    }

}
