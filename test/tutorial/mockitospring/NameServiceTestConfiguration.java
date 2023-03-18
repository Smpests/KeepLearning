package tutorial.mockitospring;

import tutorial.mockitospring.service.NameService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

// @Profile注解告诉Spring只有当test环境激活时该配置才有效
// 即其他环境运行时不会产生该Bean
@Profile("test")
@Configuration
public class NameServiceTestConfiguration {

    // @Primary用于当自动装配出现同类型Bean时优先选择
    // 也可用@Qualifier定义不同的名字解决
    // 在这里用于测试时的注入Bean来自于此而不是真实的Bean
    @Primary
    @Bean
    public NameService nameService() {
        // 返回mock的Bean对象
        return Mockito.mock(NameService.class);
    }
}
