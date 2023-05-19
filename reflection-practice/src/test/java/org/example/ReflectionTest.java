package org.example;

import org.example.annotation.Controller;
import org.example.annotation.Service;
import org.example.model.User;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Controller 애노테이션이 설정돼 있는 모든 클래스를 찾아서 출력한다.
 */
public class ReflectionTest {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

    @Test
    void controllerScan() {
        Set<Class<?>> beans = getTypesAnnotatedWith(List.of(Controller.class, Service.class));

        logger.debug("beans : [{}]", beans);
    }

    @Test
    void showClass() {
        Class<User> userClass = User.class;
        logger.debug(userClass.getName());

        logger.debug("User all decleared fields: [{}]", Arrays.stream(userClass.getDeclaredFields()).collect(Collectors.toList()));
        logger.debug("User all decleared constructors: [{}]", Arrays.stream(userClass.getDeclaredConstructors()).collect(Collectors.toList()));
        logger.debug("User all decleared methods: [{}]", Arrays.stream(userClass.getDeclaredMethods()).collect(Collectors.toList()));

    }

    // 힙영역에 로드되어 있는 객체를 가져오는 방법
    @Test
    void load() throws ClassNotFoundException {
        // 1
        Class<User> userClass = User.class;

        //2
        User user = new User("hyeon", "hyongu");
        Class<? extends User> userClass2 = user.getClass();

        //3
        Class<?> userClass3 = Class.forName("org.example.model.User");

        logger.debug("userClass: [{}]", userClass);
        logger.debug("userClass2: [{}]", userClass2);
        logger.debug("userClass3: [{}]", userClass3);

        assertThat(userClass == userClass2).isTrue();
        assertThat(userClass2 == userClass3).isTrue();
        assertThat(userClass == userClass3).isTrue();


    }

    private static Set<Class<?>> getTypesAnnotatedWith(List<Class<? extends Annotation>> annotations) {
        Reflections reflections = new Reflections("org.example");

        Set<Class<?>> beans = new HashSet<>();
        annotations.forEach(annotation -> beans.addAll(reflections.getTypesAnnotatedWith(annotation)));

        return beans;
    }


}
