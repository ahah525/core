package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 애플리케이션 설정(구성) 정보
@Configuration
public class AppConfig {
    // @Bean memberService -> new MemoryMemberRepository()
    // @Bean orderService -> new MemoryMemberRepository()
    // MemberService 역할 스프링 빈 등록
    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        // 생성자 주입
        return new MemberServiceImpl(memberRepository());
    }

    // MemberRepository 역할 스프링 빈 등록
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();    // 메모리 회원 저장소로
    }

    // OrderService 역할 스프링 빈 등록
    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        // 생성자 주입
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    // DiscountPolicy 역할 스프링 빈 등록
    @Bean
    public DiscountPolicy discountPolicy() {
        //return new FixDiscountPolicy();     // 고정 할인 정책으로
        return new RateDiscountPolicy();    // 정률 할인 정책으로 변경
    }
}
