package org.apache.rocketmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.event.CartItemEvent;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

@Slf4j
@SpringBootApplication
public class CartEventConsumer {

    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    public CartEventConsumer(RocketMQTemplate rocketMQTemplate) {
        this.rocketMQTemplate = rocketMQTemplate;
    }

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
            // 将消费结果作为消息发送，相当于回调通知生产者消费结果
            cartItemEvent.setQuantity(cartItemEvent.getQuantity() + 1);
            rocketMQTemplate.convertAndSend("cart-item-add-callback-topic", cartItemEvent);
            log.info("Send result.");
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
            // 将消费结果作为消息发送
            cartItemEvent.setQuantity(cartItemEvent.getQuantity() - 1);
            rocketMQTemplate.convertAndSend("cart-item-removed-callback-topic", cartItemEvent);
            log.info("Send result.");
        }
    }



}
