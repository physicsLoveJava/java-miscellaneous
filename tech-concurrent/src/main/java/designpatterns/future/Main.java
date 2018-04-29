package designpatterns.future;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Client client = new Client();
        Data<String> response = client.get("http://www.baidu.com");
        response.completion(new Data.DataCallback<String>() {
            @Override
            public void process(String data) {
                System.out.println("process start");
                System.out.println(data);
                System.out.println("process end");
            }
        });
        response.awaitCompletion();
//        response.awaitCompletion(1000);
    }

}
