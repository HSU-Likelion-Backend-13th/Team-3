package com.example.woodgeon.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {

        // {name => value} 값으로 전달됨. 안녕하세요. {data => Guest!!}
        model.addAttribute("data", "Guest!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // http body에 data를 그대로 내려주는 어노테이션
    public String helloString(@RequestParam("name") String name) {
        return "Hello " + name; // ex) hello ${name}
    }


    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello(); // Hello Object Create
        hello.setName(name);
        return hello; // JSON 반환 Default
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}
