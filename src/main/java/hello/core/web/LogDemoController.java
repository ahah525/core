package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

// MyLogger 가 잘 작동하는지 확인하는 테스트용 컨트롤러
@Controller
@RequiredArgsConstructor    // 자동 생성자 주입
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger;
    
    // log-demo 요청이 오면 웹 브라우저에 데이터를 문자로 반환
    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        // HttpServletRequest: 클라이언트 요청 정보
        String requestURL = request.getRequestURL().toString(); // 클라이언트가 요청한 URL 반환
        myLogger.setRequestURL(requestURL); // myLogger 의 requestURL 에 값 저장

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }

}
