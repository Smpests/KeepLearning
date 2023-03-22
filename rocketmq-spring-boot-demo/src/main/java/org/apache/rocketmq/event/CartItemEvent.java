package org.apache.rocketmq.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemEvent {
    private String itemId;
    private int quantity;
}
