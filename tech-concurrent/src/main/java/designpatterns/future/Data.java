package designpatterns.future;

public interface Data<T> {

    boolean isDone();

    T getData();

    void completion(DataCallback<T> callback);

    void awaitCompletion() throws InterruptedException;

    void awaitCompletion(long times) throws InterruptedException;

    void awaitCompletion(long times, int nanos) throws InterruptedException;

    interface DataCallback<T> {
        void process(T data);
    }

}
