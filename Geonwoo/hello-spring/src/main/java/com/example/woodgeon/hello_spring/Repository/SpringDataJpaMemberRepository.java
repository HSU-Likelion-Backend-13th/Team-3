package com.example.woodgeon.hello_spring.Repository;

import com.example.woodgeon.hello_spring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 인터페이스가 인터페이스를 받을 때에는 extends
// Spring Container 에서 이 인터페이스에 대한 구현체를 만들고 Spring Bean에 등록 해놓는다.
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    @Override
    Optional<Member> findByName(String name);
}
