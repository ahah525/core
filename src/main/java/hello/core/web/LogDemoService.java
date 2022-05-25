package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor    // 자동 생성자 주입
public class LogDemoService {

    private final MyLogger myLogger;    // 가짜 프록시 myLogger 주입
    public void logic(String id) {
        // ObjectProvider로 myLogger 조회(DL)
        myLogger.log("service id = " + id);
    }
}
