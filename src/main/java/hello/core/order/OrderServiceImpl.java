package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);            // 해당 id를 가진 회원 조회
        int discountPrice = discountPolicy.discount(member, itemPrice); // 회원의 등급에 따른 할인 금액

        return new Order(memberId, itemName, itemPrice, discountPrice); // 최종 생성된 주문 반환
    }
}
