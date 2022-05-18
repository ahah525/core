package hello.core.scan.filter;

import java.lang.annotation.*;

// @MyIncludeComponent가 붙은 클래스는 컴포넌트 스캔에 추가
@Target(ElementType.TYPE)   // 클래스 레벨에 붙음
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {
}
