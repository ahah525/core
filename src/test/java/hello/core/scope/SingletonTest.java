package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    @Test
    void singletonBeanFind() {
        // SingletonBean 직접 등록
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);

        SingletonBean singletonBean1 = ac.getBean(SingletonBean.class);
        SingletonBean singletonBean2 = ac.getBean(SingletonBean.class);
        System.out.println("singletonBean1 = " + singletonBean1);
        System.out.println("singletonBean2 = " + singletonBean2);
        // 두 인스턴스가 같은지 검증
        assertThat(singletonBean1).isSameAs(singletonBean2);

        ac.close(); // 스프링 컨테이너 종료
    }

    // 싱글톤 스코프 빈
    @Scope("singleton") // 디폴트로 생략O
    static class SingletonBean {
        // 초기화 메서드
        @PostConstruct  
        public void init() {
            System.out.println("SingletonBean.init");
        }

        // 종료 메서드
        @PreDestroy
        public void destroy() {
            System.out.println("SingletonBean.destroy");
        }
    }
}
