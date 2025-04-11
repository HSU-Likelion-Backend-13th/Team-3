package hello.core;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class HelloLombok {
    private String name; //이름 선언
    private int age; //나이를 정수형으로 선언

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("Lombok"); //@Setter와 @Getter이 없으면 생성자를 만들어줘야 한다

        String name = helloLombok.getName();
        System.out.println("name = " + name);
    }
}
