package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor    // final이 붙은 필드를 파라미터로 갖는 생성자 자동으로 생성
public class OrderServiceImpl implements OrderService{
    // 생성자 주입을 쓰면 final 키워드를 사용해 생성자에서 초기화 누락을 방지, 불변하도록 설계O
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

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
