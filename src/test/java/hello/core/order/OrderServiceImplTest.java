package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    // 순수 자바로 OrderServiceImpl 테스트
    @Test
    void createOrder() {
        // 자바코드로 테스트를 하기 위해 MemoryMemberRepository, Member, FixDiscountPolicy 인스턴스 생성
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000);
        // 생성한 주문의 할인 금액이 1000원이 맞는지 검증
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}