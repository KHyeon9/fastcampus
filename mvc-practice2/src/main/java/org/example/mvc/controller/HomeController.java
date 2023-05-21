package org.example.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.example.annotation.RequestMapping;
import org.example.mvc.annotation.Controller;

@Controller
public class HomeController{
    @RequestMapping(value = "/", method = RequestMathod.GET)
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "home";
    }
}
