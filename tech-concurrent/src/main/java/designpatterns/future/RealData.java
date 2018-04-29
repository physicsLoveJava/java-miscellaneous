package designpatterns.future;

public class RealData<T> implements Data<T> {

    private T data;

    private boolean isDone;

    @Override
    public boolean isDone() {
        return isDone;
    }

    @Override
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.isDone = true;
        this.data = data;
    }

}
