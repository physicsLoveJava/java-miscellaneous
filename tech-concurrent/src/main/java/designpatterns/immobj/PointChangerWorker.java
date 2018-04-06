package designpatterns.immobj;

import java.util.Random;

public class PointChangerWorker implements Runnable {

    private int count = 10;

    private PointHolder holder;

    private Random random = new Random();

    private PointChanger changer;

    public PointChangerWorker(PointHolder holder, ChangerTypeEnum type) {
        this.holder = holder;
        this.changer = wrap(choose(type));
    }

    private PointChanger wrap(PointChanger changer) {
        return new PointChangerWrapper(changer);
    }

    private PointChanger choose(ChangerTypeEnum type) {
        switch (type) {
            case ADD:
                return new AddPointChangerImpl();
            case MINUS:
                return new MinusPointChangerImpl();
            case MULTIPLY:
                return new MultiplyPointChangerImpl();
            case BOUNCE:
                return new BouncePointChangerImpl();
        }
        throw new IllegalArgumentException();
    }

    @Override
    public void run() {
        while(count-- > 0) {
            int id = random.nextInt(holder.size());
            holder.change(id, changer.change(holder.get(id)));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class PointChangerWrapper implements PointChanger {

        private PointChanger changer;

        public PointChangerWrapper(PointChanger changer) {
            this.changer = changer;
        }

        @Override
        public Point change(Point x) {
            Point y = changer.change(x);
            record(x, y);
            return y;
        }

        private void record(Point x, Point y) {
            System.out.printf("%s -> %s \n", x, y);
        }
    }

    class AddPointChangerImpl implements PointChanger {

        @Override
        public Point change(Point p) {
            return new Point( p.getX() + 5, p.getY() + 5);
        }
    }

    class MinusPointChangerImpl implements PointChanger {

        @Override
        public Point change(Point p) {
            return new Point( p.getX() - 5, p.getY() - 5);
        }
    }

    class MultiplyPointChangerImpl implements PointChanger {

        @Override
        public Point change(Point p) {
            return new Point( p.getX() * 2, p.getY() * 2);
        }
    }

    class BouncePointChangerImpl implements PointChanger {

        Random random = new Random();

        @Override
        public Point change(Point p) {
            int i = (int) Math.signum(random.nextInt(1) - 0.5);
            return new Point( p.getX() * i, p.getY() * i);
        }
    }

}
