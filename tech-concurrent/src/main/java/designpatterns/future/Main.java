package designpatterns.future;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Client client = new Client();
        Data<String> response = client.get("http://www.baidu.com");
        while (!response.isDone()) {
            Thread.sleep(1000);
        }
        System.out.println(response.getData());
    }

}
