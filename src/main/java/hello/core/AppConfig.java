package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {
    // MemberService 역할(생성자 주입)
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    // MemberRepository 역할
    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();    // 메모리 회원 저장소로
    }

    // OrderService 역할 (생성자 주입)
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    // DiscountPolicy 역할
    public DiscountPolicy discountPolicy() {
        //return new FixDiscountPolicy();     // 고정 할인 정책으로
        return new RateDiscountPolicy();    // 정률 할인 정책으로 변경
    }
}
