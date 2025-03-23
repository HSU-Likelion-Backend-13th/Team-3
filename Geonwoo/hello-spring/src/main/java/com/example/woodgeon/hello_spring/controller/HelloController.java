package com.example.woodgeon.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {

        // {name => value} 값으로 전달됨. 안녕하세요. {data => Guest!!}
        model.addAttribute("data", "Guest!!");
        return "hello";
    }
}
