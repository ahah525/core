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

        // 프로토타입 빈 조회1
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1); // 카운트 값 검증

        // 프로토타입 빈 조회2
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1); // 카운트 값 검증
    }

    @Test
    void singletonClientUsePrototype() {
        // ClientBean, PrototypeBean 빈 등록
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        // clientBean(싱글톤빈) 조회1
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        // clientBean(싱글톤빈) 조회2
        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(2);
    }

    // 싱글톤 빈
    @Scope("singleton") // 생략O
    static class ClientBean {
        private final PrototypeBean prototypeBean;  // ClientBean 생성 시점에 주입

        // prototypeBean 의존관계 주입
        @Autowired  // 생략O
        ClientBean(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }

        public int logic() {
            prototypeBean.addCount();   // prototypeBean 의 addCount() 호출
            int count = prototypeBean.getCount();
            return count;   // 카운트 반환
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