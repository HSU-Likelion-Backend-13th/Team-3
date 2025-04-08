package hello.core.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NetworkClient {

    private String url;
    public NetworkClient() {
        System.out.println("생성자 호출, url: " + url);
    }
    // start
    private void connect() {
        System.out.println("connect: " + url);
    }

    private void call(String message) {
        System.out.println("call: " + url + " message: " + message);
    }
    // end
    private void disconnect() {
        System.out.println("close: " + url);
    }

    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("Init");
    }

    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
