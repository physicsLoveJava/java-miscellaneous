package com.lujian.casual.event;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public abstract class Element implements Event{

    private String name;
    private EnumMap<EventType, List<EventAction>> actionMap;
    private EventDispatcher dispatcher;

    protected Element(String name) {
        this.name = name;
        this.actionMap = new EnumMap<EventType, List<EventAction>>(EventType.class);
        this.dispatcher = EventDispatcher.get();
    }

    @Override
    public synchronized void addListener(EventType type, EventAction action) {
        if(!actionMap.containsKey(type)) {
            actionMap.put(type, new ArrayList<EventAction>());
        }
        actionMap.get(type).add(action);
    }

    @Override
    public synchronized void trigger(EventType type) {
        if(!actionMap.containsKey(type)) {
            return;
        }
        List<EventAction> actions = actionMap.get(type);
        for (EventAction action : actions) {
            dispatcher.dispatch(this, type, action);
        }
    }

    public String getName() {
        return name;
    }
}
