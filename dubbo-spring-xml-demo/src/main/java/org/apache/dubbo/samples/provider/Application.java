package org.apache.dubbo.samples.provider;

import java.util.concurrent.CountDownLatch;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo-demo-provider.xml");
        context.start();

        // 挂起主线程，防止退出
        new CountDownLatch(1).await();
    }
}