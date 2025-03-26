package com.example.woodgeon.hello_spring;

import com.example.woodgeon.hello_spring.Repository.JdbcMemberRepository;
import com.example.woodgeon.hello_spring.Repository.JpaMemberRepsoitory;
import com.example.woodgeon.hello_spring.Repository.MemberRepository;
import com.example.woodgeon.hello_spring.Repository.MemoryMemberRepository;
import com.example.woodgeon.hello_spring.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    // @PersistenceContext
    private final EntityManager em;
    // DI
    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    // 구현체만 갈아끼우기만 하면 된다.
    @Bean
    public MemberRepository memberRepository() {
        // return new MemoryMemberRepository();
        // return new JdbcMemberRepository(dataSource);
        // return new JdbcMemberRepository(dataSource);
        return new JpaMemberRepsoitory(em);
    }

}
