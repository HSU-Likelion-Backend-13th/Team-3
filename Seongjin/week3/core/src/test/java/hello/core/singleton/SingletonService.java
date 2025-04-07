package hello.core.singleton;

public class SingletonService {

    // Eager Initialization 방식 사용
    // 1. static 영역에 미리 생성된 단 하나의 인스턴스를 보관
    private static final SingletonService instance = new SingletonService();

    // 2. 인스턴스에 접근할 수 있는 유일한 static 메서드
    public static SingletonService getInstance() {
        return instance;
    }

    // 3. 외부에서 new 키워드로 생성하지 못하도록 생성자를 private으로 막음
    private SingletonService() {}
    // SingletonTest 확인

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}