package tutorial.dynamicproxy;

public class TestProxy {
    public static void main(String[] args) {
        HelloProxy helloProxy = new HelloProxy();
        HelloInterface hello = (HelloInterface) helloProxy.bind(new Hello());
        hello.sayHello();
        hello.sayBye();
    }
}
