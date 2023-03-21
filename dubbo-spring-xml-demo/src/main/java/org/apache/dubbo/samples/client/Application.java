package org.apache.dubbo.samples.client;

import java.io.IOException;

import org.apache.dubbo.samples.api.GreetingsService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo-demo-consumer.xml");
        context.start();
        GreetingsService greetingsService = (GreetingsService) context.getBean("greetingsService");

        String message = greetingsService.sayHi("dubbo");
        System.out.println("Receive result ======> " + message);
        System.in.read();
        System.exit(0);
    }

}
