package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{
    // 인터페이스에만 의존하도록 변경(DIP 만족)
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    // 의존관계 자동 주입(DI)
    @Autowired  // ac.getBean(MemberRepository.class), ac.getBean(DiscountPolicy.class)
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);            // 해당 id를 가진 회원 조회
        int discountPrice = discountPolicy.discount(member, itemPrice); // 회원의 등급에 따른 할인 금액

        return new Order(memberId, itemName, itemPrice, discountPrice); // 최종 생성된 주문 반환
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
