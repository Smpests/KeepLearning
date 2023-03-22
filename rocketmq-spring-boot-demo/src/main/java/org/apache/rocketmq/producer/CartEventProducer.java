package org.apache.rocketmq.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.event.CartItemEvent;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class CartEventProducer implements CommandLineRunner {

    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    public CartEventProducer(RocketMQTemplate rocketMQTemplate) {
        this.rocketMQTemplate = rocketMQTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        // 异步传输
        rocketMQTemplate.convertAndSend("cart-item-add-topic",
                new CartItemEvent("bike", 1));
        rocketMQTemplate.convertAndSend("cart-item-add-topic",
                new CartItemEvent("computer", 2));
        rocketMQTemplate.convertAndSend("cart-item-removed-topic",
                new CartItemEvent("bike", 1));

        // 同步传输，可通过SentResult判断是否传输成功，高可靠性场景下
//        SendResult addBikeResult = rocketMQTemplate.syncSend("cart-item-add-topic",
//                new CartItemEvent("bike", 1));
//        SendResult addComputerResult = rocketMQTemplate.syncSend("cart-item-add-topic",
//                new CartItemEvent("computer", 2));
//        SendResult removeBikeResult = rocketMQTemplate.syncSend("cart-item-removed-topic",
//                new CartItemEvent("bike", 1));

        // 异步传输及通知回调，高吞吐量场景
//        rocketMQTemplate.asyncSend("cart-item-add-topic", new CartItemEvent("bike", 1), new SendCallback() {
//            @Override
//            public void onSuccess(SendResult sendResult) {
//                log.error("Successfully sent cart item");
//            }
//
//            @Override
//            public void onException(Throwable throwable) {
//                log.error("Exception during cart item sending", throwable);
//            }
//        });

        // sendOneWay()发送，不保证消息被发送，超高吞吐量需求场景（如日志收集）
    }

    public static void main(String[] args) {
        SpringApplication.run(CartEventProducer.class, args);
    }
}
