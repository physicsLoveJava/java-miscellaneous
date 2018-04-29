package designpatterns.future;

public interface Data<T> {

    boolean isDone();

    T getData();

}
