package tutorial.mockitospring;

import tutorial.mockitospring.MockApplication;
import tutorial.mockitospring.service.NameService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.ArgumentMatchers.*;

@ActiveProfiles("test")
@SpringBootTest(classes = MockApplication.class)
public class MockBeanAnnotationIntegrationTest {

    // spring test中的注解
    @MockBean
    NameService nameService;

    @Autowired
    ApplicationContext context;

    @Test
    public void useMockBean_WhenMethodInvoked_THenMockValueReturned() {
        Mockito.when(nameService.getGlobalUniqueName(anyString())).thenReturn("Mock id");
        NameService serviceFromContext = context.getBean(NameService.class);

        Assertions.assertEquals("Mock id", serviceFromContext.getGlobalUniqueName("id"));
        // 验证是否调用了Mock的Bean
        Mockito.verify(nameService, Mockito.times(1)).getGlobalUniqueName(anyString());
    }
}
