package com.lujian.casual.event;

public interface Event {

    void addListener(EventType type, EventAction action);

    void trigger(EventType type);
}
