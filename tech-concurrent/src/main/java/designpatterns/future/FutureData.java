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

}
