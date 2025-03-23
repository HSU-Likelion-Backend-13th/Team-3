package com.example.woodgeon.hello_spring.controller;

import com.example.woodgeon.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// Spring Container 에서 Bean 으로 관리
@Controller
public class MemberController {

    private final MemberService memberService;

    // MemberController 가 생성될 때 Spring bean에 등록된 memberService 객체를 가져와서 넣어줌. Dependency Injection
    // (Constructor Injection)
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // (Field Injection)
    // @Autowired private MemberService memberService;

    // (Setter Injection)
    // @Autowired
    // public void setMemberService(MemberService memberService) {
    //     this.memberService = memberService;
    // }
}
