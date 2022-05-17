package hello.core.singleton;

public class SingletonService {
    // 싱글톤 패턴 구현: 객체를 미리 생성해두는 가장 단순, 안전한 방법
    
    // 1. static 영역에 객체를 딱 1개만 생성한다. (외부에서 접근하지 못하도록 private으로)
    private static final SingletonService instance = new SingletonService();

    // 2. Singleton 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 public으로 선언한다.
    public static SingletonService getInstance() {
        return instance;
    }
    
    // 3. private 생성자(외부에서 new로 객체를 생성하는 것을 막기 위함)
    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
