package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor    // 자동 생성자 주입
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger;
    
    // log-demo 요청이 오면 문자 바로 반환
    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        // 고객 요청 정보를 받을 수 있음
        String requestURL = request.getRequestURL().toString(); // 고객이 어떤 URL로 요청했는지
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }

}
