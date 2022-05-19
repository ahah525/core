package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@MainDiscountPolicy
//@Qualifier("mainDiscountPolicy")    // 문자는 컴파일시에 오류 잡을 수X
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
