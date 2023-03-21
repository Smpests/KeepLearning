package org.qingsong.mockitospring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 订单服务，调NameService生成唯一订单ID
 */
@Service
public class OrderService {

    private NameService nameService;
    /**
     * Autowired建议从构造器、setter注入
      */
    @Autowired
    public OrderService(NameService nameService) {
        this.nameService = nameService;
    }

    public String getOrderId(String id) {
        return nameService.getGlobalUniqueName(id);
    }
}
