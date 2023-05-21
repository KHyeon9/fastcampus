package org.example.mvc;

import org.example.annotation.RequestMapping;
import org.example.mvc.annotation.Controller;
import org.example.mvc.controller.HandlerKey;
import org.example.mvc.controller.RequestMathod;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AnnotationHandlerMapping implements HandlerMapping{
    private final Object[] basePackage;
    private Map<HandlerKey, AnnotationHandler> handlers = new HashMap<>();

    public AnnotationHandlerMapping(Object... basePackage) {
        this.basePackage = basePackage;
    }

    public void initialize() {
        Reflections reflections = new Reflections(basePackage);

        // HomeController
        Set<Class<?>> clazzesWhithControllerAnnotation = reflections.getTypesAnnotatedWith(Controller.class);

        clazzesWhithControllerAnnotation.forEach(clazz ->
                Arrays.stream(clazz.getDeclaredMethods()).forEach(declaredMethod ->{
                    RequestMapping requestMapping = declaredMethod.getDeclaredAnnotation(RequestMapping.class);

                    // @RequestMapping(value = "/", method = RequestMathod.GET) 이 값을 가리킴
                    Arrays.stream(getRequestMethods(requestMapping))
                            .forEach(requestMathod -> handlers.put(
                                    new HandlerKey(requestMathod, requestMapping.value()), new AnnotationHandler(clazz, declaredMethod)
                            ));
                })
        );
    }

    private RequestMathod[] getRequestMethods(RequestMapping requestMapping) {
        return requestMapping.method();
    }

    @Override
    public Object findHandler(HandlerKey handlerKey) {
        return handlers.get(handlerKey);
    }
}
