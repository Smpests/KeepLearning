package dynamicproxy;

public class Hello implements HelloInterface {
    public void sayHello() {
        System.out.println("Hello world.");
    }
    public void sayBye() {
        System.out.println("Bye world.");
    }
}
