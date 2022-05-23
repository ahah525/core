package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;

public class PrototypeTest {

    @Test
    void prototypeBeanFind() {
        // PrototypeBean 직접 등록
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);
        // 두 인스턴스가 다른지 검증
        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        /*
        // 클라이언트갸 종료 메서드 직접 호출해야함
        prototypeBean1.destroy();
        prototypeBean2.destroy();
         */
        ac.close(); // 스프링 컨테이너 종료
    }

    // 프로토타입 스코프 빈
    @Scope("prototype")
    static class PrototypeBean {
        // 초기화 메서드
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        // 종료 메서드
        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
