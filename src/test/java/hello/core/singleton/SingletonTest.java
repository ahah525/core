package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {
    
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        // 1. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        // 2. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        // 위에서 생성한 객체 2개의 참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberService1 != memberService2 (두 객체가 다른지) 검증
        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        // private으로 생성자를 막아둠(외부에서 생성하면 컴파일 오류)
        // new SingletonService();
        
        // 1. 조회: 호출할 때 마다 같은 객체 반환
        SingletonService singletonService1 = SingletonService.getInstance();
        // 2. 조회: 호출할 때 마다 같은 객체 반환
        SingletonService singletonService2 = SingletonService.getInstance();

        // getInstance()로 얻은 객체 인스턴스 2개의 참조값이 같은지 확인
        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        // singletonServcie1 == singletonService2 (두 객체 인스턴스가 같은지 검증)
        assertThat(singletonService1).isSameAs(singletonService2);

        singletonService1.logic();
    }
}
