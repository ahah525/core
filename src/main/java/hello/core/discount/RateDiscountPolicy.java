package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("mainDiscountPolicy")    // 추가 구분자
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
