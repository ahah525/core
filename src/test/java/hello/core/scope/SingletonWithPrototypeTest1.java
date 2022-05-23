package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

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

    // ObjectProvider 사용
    @Test
    void providerTest() {
        // ClientBean, PrototypeBean 빈 등록
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        // 클라이언트 요청1
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        // 클라이언트 요청2
        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }

    // 싱글톤 빈
    @Scope("singleton") // 생략O
    static class ClientBean {

        // javax.inject
        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;
        
        public int logic() {
            // logic() 호출할 때마다 프로토타입 빈을 대신 조회해서 반환(DL)
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
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