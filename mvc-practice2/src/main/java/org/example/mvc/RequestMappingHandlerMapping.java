package org.example.mvc;

import org.example.mvc.controller.RequestMathod;
import org.example.mvc.controller.*;

import java.util.HashMap;
import java.util.Map;

public class RequestMappingHandlerMapping implements HandlerMapping {
    //  [key] /users [value] UserController
    private Map<HandlerKey, Controller> mappings = new HashMap<>();

    void init() {
        // mappings.put(new HandlerKey(RequestMathod.GET, "/"), new HomeController());
        mappings.put(new HandlerKey(RequestMathod.GET, "/users"), new UserListController());
        mappings.put(new HandlerKey(RequestMathod.POST, "/users"), new UserCreateController());
        mappings.put(new HandlerKey(RequestMathod.GET, "/user/form"), new ForwardController("/user/form"));
    }

    public Controller findHandler(HandlerKey handlerKey) {

        return mappings.get(handlerKey);
    }
}
