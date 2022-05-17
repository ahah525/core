package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

// 설정 정보
@Configuration
// @Component가 붙은 클래스를 자동으로 스프링 빈으로 등록(@Configuration이 붙은 클래스는 제외)
@ComponentScan(
       excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
}
