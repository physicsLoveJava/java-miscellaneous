package designpatterns.future;

public class RealData<T> implements Data<T> {

    private T data;

    private boolean isDone;

    private Thread thread;

    @Override
    public boolean isDone() {
        return isDone;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public void completion(final DataCallback<T> callback) {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isDone()) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                callback.process(data);
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void awaitCompletion() throws InterruptedException {
        thread.join();
    }

    @Override
    public void awaitCompletion(long times) throws InterruptedException {
        thread.join(times);
    }

    @Override
    public void awaitCompletion(long times, int nanos) throws InterruptedException {
        thread.join(times, nanos);
    }

    public void setData(T data) {
        this.isDone = true;
        this.data = data;
    }

}
