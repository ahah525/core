package hello.core;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter // getter 자동 생성
@Setter // setter 자동 생성
@ToString   // toString() 자동 생성
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("asdfas");

        System.out.println("helloLombok = " + helloLombok);
    }
}
