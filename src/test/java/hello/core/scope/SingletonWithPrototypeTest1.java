package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        // PrototypeBean 빈 등록
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1); // 카운트 값 검증

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1); // 카운트 값 검증
    }

    // 싱글톤 빈
    static class ClientBean {
        private final PrototypeBean prototypeBean;

        // 생성자 주입
        @Autowired
        ClientBean(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }
    }
    
    // 프로토타입 빈
    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        // 카운트 증가 메서드
        public void addCount() {
            count++;
        }

        // 카운트 반환 메서드
        public int getCount() {
            return count;
        }
        
        // 초기화 메서드
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);   // 참조값 출력
        }
        
        // 종료 메서드
        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
