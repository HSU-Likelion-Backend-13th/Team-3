package com.example.woodgeon.hello_spring;

import com.example.woodgeon.hello_spring.Repository.MemberRepository;
import com.example.woodgeon.hello_spring.Repository.MemoryMemberRepository;
import com.example.woodgeon.hello_spring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

}
