package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

// 설정 정보
// basePackages: 탐색할 패키지의 시작 위치 지정(해당 패키지+하위 패키지)
// excludeFilters: 컴포넌트 시캔에서 제외할 대상 지정
@Configuration
// @Component가 붙은 클래스를 자동으로 스프링 빈으로 등록(@Configuration이 붙은 클래스는 제외)
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    // 수동 빈 등록
    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
