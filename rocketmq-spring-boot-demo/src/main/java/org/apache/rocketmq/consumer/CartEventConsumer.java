package org.apache.rocketmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.event.CartItemEvent;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

@Slf4j
@SpringBootApplication
public class CartEventConsumer {
    public static void main(String[] args) {
        SpringApplication.run(CartEventConsumer.class, args);
    }

    // 为不同message topic分别编写消费者监听消息

    @Service
    @RocketMQMessageListener(
            topic = "cart-item-add-topic",
            consumerGroup = "cart-consumer_cart-item-add-topic"
    )
    public class CartItemAddConsumer implements RocketMQListener<CartItemEvent> {

        @Override
        public void onMessage(CartItemEvent cartItemEvent) {
            log.info("Adding item: {}", cartItemEvent);
        }
    }

    @Service
    @RocketMQMessageListener(
            topic = "cart-item-removed-topic",
            consumerGroup = "cart-consumer_cart-item-removed-topic"
    )
    public class CartItemRemoveConsumer implements RocketMQListener<CartItemEvent> {
        @Override
        public void onMessage(CartItemEvent cartItemEvent) {
            log.info("Removing item: {}", cartItemEvent);
        }
    }
}
