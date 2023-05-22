package org.example.di;

import org.example.annotation.Inject;
import org.example.controller.UserController;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class BeanFactory {
    private final Set<Class<?>> preInstantiatedClazz;
    private Map<Class<?>, Object> beans = new HashMap<>();

    public BeanFactory(Set<Class<?>> preInstantiatedClazz) {
        this.preInstantiatedClazz = preInstantiatedClazz;
        initialize();
    }

    private void initialize() {
        for (Class<?> clazz : preInstantiatedClazz) {
            Object instance = createInstance(clazz);
            beans.put(clazz, instance);
        }
    }

    // 1. UserController 가 들어옴
    // 4. UserService가 들어옴
    // 6. UserService가 들어와서 인스턴스가 생성되어 다시 UserController(1번으로)로 돌아옴
    private Object createInstance(Class<?> clazz) {
        // 생성자
        Constructor<?> constructor = findContrictor(clazz);

        // 파라미터
        List<Object> parameters = new ArrayList<>();
        for (Class<?> typeClass : constructor.getParameterTypes()) {
            // 2. typeClass에서 UserService를 가져옴
            parameters.add(getParameterByClass(typeClass));
        }

        //인스턴스 생성
        try {
            // 5. UserService의 경우 파라미터가 없기때문에 for문 통과되어 생성
            // 7. UserService가 생성됨으로써 UserControlle 인스턴스 생성
            return constructor.newInstance(parameters.toArray());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e){
            throw new RuntimeException(e);
        }

    }

    private Constructor<?> findContrictor(Class<?> clazz) {
        Constructor<?> constructor = BeanFactoryUtils.getInjectedConstructor(clazz);

        if (Objects.nonNull(constructor)) {
            return constructor;
        }
        return clazz.getConstructors()[0];
    }

    private Object getParameterByClass(Class<?> typeClass) {
        // 3. 하지만 UserService가 없으므로 createInstance(UserService)로 들어감
        Object instanceBean = getBean(typeClass);
        if (Objects.nonNull(instanceBean)) {
            return instanceBean;
        }
        return createInstance(typeClass);
    }

    public <T> T getBean(Class<?> requiredType) {
        return (T) beans.get(requiredType);
    }
}
