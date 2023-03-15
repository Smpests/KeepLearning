package mockito;

import mockitospring.service.NameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalMatchers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MockitoAnnotationTest {

    // @BeforeEach在每个测试方法执行前执行
    @BeforeEach
    public void init() {
        // Mock对象时必须调用
        MockitoAnnotations.initMocks(this);
    }

    // @Mock假对象，模拟执行方法
    @Mock
    List<String> mockList;

    // @Spy会new对象，执行真实方法
    @Spy
    List<String> spyList = new ArrayList<>();

    // 测试类的被测对象使用@InjectMock，会自动将@Mock注解的域注入到myClass中
    // @InjectMocks
    // MyClass myClass;

    // @Captor捕获参数值
    @Captor
    ArgumentCaptor argumentCaptor;

    String emptyString;

    @Test
    public void stubbing() {
        mockList.add("A");
        spyList.add("B");
        // verify的作用：确认mock对象的方法是否被调用，以及参数是否正确
        verify(mockList).add(anyString());
        // 捕获调用add()时传的是什么参数，用于下面的断言判断是不是传的A，效果和上面差不多
        verify(mockList).add((String) argumentCaptor.capture());
        assertEquals("A", argumentCaptor.getValue());
        verify(spyList).add("B");
        // 下面的断言会报错，并没有实际添加到mockList中
        // assertEquals(1, mockList.size());
        // 下面的断言通过，因为spy对象操作了真实的方法
        assertEquals(1, spyList.size());

        // doReturn不会调用真实的方法，直接返回设定的值
        doReturn(100).when(spyList).size();
        assertEquals(100, spyList.size());

        // thenReturn回调用方法然后返回设定值
        when(mockList.size()).thenReturn(100);
        assertEquals(100, mockList.size());
    }

    @Test
    public void coverException() {
        // 断言抛出异常，如果方法中有异常分支，可用下面的方法覆盖测试
        // @Mock和@Spy都可以
        // 有返回值
        when(mockList.get(anyInt())).thenThrow(IndexOutOfBoundsException.class);
        assertThrows(IndexOutOfBoundsException.class, () -> {
           mockList.get(0);
        });
        // 无返回值
        doThrow(IllegalStateException.class).when(mockList).add(anyString());
        assertThrows(IllegalStateException.class, () -> {
            mockList.add("A");
        });
        // 异常带参数
        when(mockList.remove(anyInt())).thenThrow(new NullPointerException("Error occurred"));
        assertThrows(NullPointerException.class, () -> {
            mockList.remove(0);
        });
    }

    @Test
    public void mockitoArgumentMatcher() {
        mockList.add("test");
        // 任意匹配，还有anyInt()、anyCollection()等
        // any(My.class)，匹配任意自定义类
        verify(mockList).add(any(String.class));
        // argThat(自定义实现了ArgumentMatcher的匹配类)
        verify(mockList).add(anyString());
        // 相等匹配
        verify(mockList).add(eq("test"));
        // 或匹配，满足两个匹配中的任一个即可
        verify(mockList).add(or(eq("A"), endsWith("t")));
    }

    @Test
    public void mockIntoMethodParameter(@Mock NameService service) {
        lenient().when(service.getGlobalUniqueName(anyString())).thenReturn("mock");
        verify(service).getGlobalUniqueName(anyString());
    }
}
