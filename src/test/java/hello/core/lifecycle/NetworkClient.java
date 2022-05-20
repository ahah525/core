package hello.core.lifecycle;

import java.sql.SQLOutput;

// 가상 네트워크 클라이언트
public class NetworkClient {

    private String url; // 접속해야할 서버 url

    // 디폴트 생성자
    public NetworkClient() {
        System.out.println("생성자를 호출, url = " + url);
        connect();
        call("초기화 연결 메시지");
    }

    // 외부에서 url 설정하기
    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    // 서버에 연결이 된 상태에서 서버에 메시지 던지기
    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    // 서비스 종료시 호출(안전하게 서비스 연결 끊어짐)
    public void disConnect() {
        System.out.println("close: " + url);
    }
}
