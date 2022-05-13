package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount = 1000;   // 고정 할인 금액(1000원 할인)

    // 등급에 따른 할인 금액 반환 메서드
    @Override
    public int discount(Member member, int price) {
        // enum 타입은 == 으로 비교
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
