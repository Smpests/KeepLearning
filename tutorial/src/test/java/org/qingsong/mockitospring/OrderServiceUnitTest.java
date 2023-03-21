package org.qingsong.mockitospring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.qingsong.mockitospring.service.NameService;
import org.qingsong.mockitospring.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@ActiveProfiles("test")
@ContextConfiguration(classes = NameServiceTestConfiguration.class)
@SpringBootTest(classes = MockApplication.class, properties = "spring.main.allow-bean-definition-overriding=true")
public class OrderServiceUnitTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private NameService nameService;

    @Test
    public void whenOrderIdIsProvided_thenRetrievedNameIsCorrect() {
        Mockito.when(nameService.getGlobalUniqueName("OrderId")).thenReturn("Mock name");
        Assertions.assertEquals("Mock name", orderService.getOrderId("OrderId"));
    }
}