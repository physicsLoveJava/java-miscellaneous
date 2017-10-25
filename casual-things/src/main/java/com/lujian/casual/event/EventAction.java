package com.lujian.casual.event;

public interface EventAction {

    void perform(EventDispatcher.EventTarget target);

}
