package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutoWiredOption() {
        // TestBean 스프링 빈 등록
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {
        // required=true 이면, 오류발생
        // 1. 자동 주입할 대상이 없으면, 수정자 메서드 호출X
        @Autowired(required = false)
        public void setNoBean1(Member noBean1) {
            // Member 는 스프링 빈이 아님
            System.out.println("noBean1 = " + noBean1);
        }

        // 2. 자동 주입할 대상이 없으면, null 호출
        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }

        // 3. 자동 주입할 대상이 없으면, Optional.empty 호출
        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }
    }
}
