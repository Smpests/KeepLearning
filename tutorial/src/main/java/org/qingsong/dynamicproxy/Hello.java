package org.qingsong.dynamicproxy;

public class Hello implements HelloInterface {
    @Override
    public void sayHello() {
        System.out.println("Hello world.");
    }
    @Override
    public void sayBye() {
        System.out.println("Bye world.");
    }
}
