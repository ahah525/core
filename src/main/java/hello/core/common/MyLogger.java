package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

// 로그 출력 클래스
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)   // request 스코프로 지정, 프록시 모드 설정
public class MyLogger {

    private String uuid;        // HTTP 요청을 구분하기 위한 id
    private String requestURL;  // 실제 요청한 URL

    // requestURL 외부에서 주입
    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    // 로그 출력 메서드
    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);
    }

    // 초기화 메서드
    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString(); // 전세계 유일한 id를 생성해서 저장
        System.out.println("[" + uuid + "] request scope bean create:" + this);
    }

    // 종료 메서드
    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close:" + this);
    }
}
