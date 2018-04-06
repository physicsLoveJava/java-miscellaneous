package designpatterns.immobj;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class PointHolder {

    private AtomicInteger count = new AtomicInteger(0);
    private Map<Integer, Point> map = new ConcurrentHashMap<>();

    public void add(Point p) {
        int id = count.getAndIncrement();
        map.put(id, p);
    }

    public void change(int id, Point p) {
        map.put(id, p);
    }

    public int size() {
        return map.size();
    }

    public Map<Integer, Point> getSnapShot() {
        return Collections.unmodifiableMap(map);
    }

    public Point get(int id) {
        return map.get(id);
    }
}
