package com.example.woodgeon.hello_spring.Repository;

import com.example.woodgeon.hello_spring.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepsoitory implements MemberRepository {

    // JPA는 EntityManager 라는 것으로 모든 동작을 한다. 내부적으로 소스를 다 들고있어서 DB와의 통신도 가능하다.
    private final EntityManager em;

    public JpaMemberRepsoitory(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        // JPA가 insert query까지 다 만들고 Id까지 자동생성 해준다.
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        // JPQL
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
}
