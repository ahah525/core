package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary    // 우선권 부여
public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10;    // 할인율(10%)
    @Override
    public int discount(Member member, int price) {
        // VIP이면 10% 할인
        if(member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
