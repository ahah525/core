package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor    // 자동 생성자 주입
public class LogDemoService {

    private final ObjectProvider<MyLogger> myLoggerProvider;    // myLogger 를 찾을 수 있는 ObjectProvider 주입
    public void logic(String id) {
        // ObjectProvider로 myLogger 조회(DL)
        MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.log("service id = " + id);
        //myLogger.log("LogDemoService-MyLogger : " + myLogger);
    }
}
