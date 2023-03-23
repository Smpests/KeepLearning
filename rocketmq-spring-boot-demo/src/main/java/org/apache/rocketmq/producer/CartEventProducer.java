package org.apache.rocketmq.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.event.CartItemEvent;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

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
        rocketMQTemplate.asyncSend("cart-item-add-topic", new CartItemEvent("bike", 1), new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("Successfully sent cart item by async.");
            }

            @Override
            public void onException(Throwable throwable) {
                log.error("Exception during cart item sending", throwable);
            }
        });

        // sendOneWay()发送，不保证消息被发送，超高吞吐量需求场景（如日志收集）

        // 事务发送
         Message<CartItemEvent> msg = MessageBuilder.withPayload(new CartItemEvent("phone", 1)).build();
         rocketMQTemplate.sendMessageInTransaction("cart-item-add-topic", msg, null);
    }

    /**
     * 将本生产者发送的消息被消费者消费后的结果再进行消费
     */
    @Service
    @RocketMQMessageListener(
            topic = "cart-item-add-callback-topic",
            consumerGroup = "cart-consumer_cart-item-add-callback-topic"
    )
    public class CartItemAddCallBackConsumer implements RocketMQListener<CartItemEvent> {

        @Override
        public void onMessage(CartItemEvent cartItemEvent) {
            log.info("Adding item result: {}", cartItemEvent);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(CartEventProducer.class, args);
    }

    @Service
    @RocketMQMessageListener(
            topic = "cart-item-removed-callback-topic",
            consumerGroup = "cart-consumer_cart-item-removed-callback-topic"
    )
    public class CartItemRemoveCallbackConsumer implements RocketMQListener<CartItemEvent> {
        @Override
        public void onMessage(CartItemEvent cartItemEvent) {
            log.info("Removing item result: {}", cartItemEvent);
        }
    }
}
