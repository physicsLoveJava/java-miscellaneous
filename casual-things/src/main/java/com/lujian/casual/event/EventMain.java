package com.lujian.casual.event;

public class EventMain {

    public static void main(String[] args) {

        XButton button = new XButton();
        XLabel label = new XLabel();
        button.addListener(EventType.CLICK, new EventAction() {
            @Override
            public void perform(EventDispatcher.EventTarget target) {
                System.out.println(target.getElementName() + ":" + target.getType());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("this is a long click button!");
            }
        });
        button.addListener(EventType.CLICK, new EventAction() {
            @Override
            public void perform(EventDispatcher.EventTarget target) {
                System.out.println(target.getElementName() + ":" + target.getType());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("this is a long long click button!");
            }
        });
        button.addListener(EventType.CHANGE, new EventAction() {
            @Override
            public void perform(EventDispatcher.EventTarget target) {
                System.out.println(target.getElementName() + ":" + target.getType());
            }
        });
        label.addListener(EventType.CLICK, new EventAction() {
            @Override
            public void perform(EventDispatcher.EventTarget target) {
                System.out.println(target.getElementName() + ":" + target.getType());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("this is a short click label!");
            }
        });

        button.trigger(EventType.CLICK);
        button.trigger(EventType.CLICK);
        button.trigger(EventType.CHANGE);
        label.trigger(EventType.CLICK);
        try {
            Thread.sleep(2000);
            System.out.println("wait to be operated");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        button.trigger(EventType.CLICK);
    }

}
