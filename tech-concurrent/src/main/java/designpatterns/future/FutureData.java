package designpatterns.future;

public class FutureData<T> implements Data<T> {

    RealData<T> realData;

    public FutureData(RealData<T> realData) {
        this.realData = realData;
    }

    @Override
    public boolean isDone() {
        return realData.isDone();
    }

    @Override
    public T getData() {
        return realData.getData();
    }

    @Override
    public void completion(DataCallback<T> callback) {
        realData.completion(callback);
    }

    @Override
    public void awaitCompletion() throws InterruptedException {
        realData.awaitCompletion();
    }

    @Override
    public void awaitCompletion(long times) throws InterruptedException {
        realData.awaitCompletion(times);
    }

    @Override
    public void awaitCompletion(long times, int nanos) throws InterruptedException {
        realData.awaitCompletion(times, nanos);
    }

}
