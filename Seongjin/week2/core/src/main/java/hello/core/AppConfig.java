package hello.core;

import hello.core.Order.OrderService;
import hello.core.Order.OrderServiceImple;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImple;
import hello.core.member.MemoryMemberRepository;

public class AppConfig {
    public MemberService memberService(){
        return new MemberServiceImple(memberRepository());
    }

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService(){
        return new OrderServiceImple(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy(){
        return new RateDiscountPolicy();
    }
}
