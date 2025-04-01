package hello.core;

import hello.core.Order.Order;
import hello.core.Order.OrderService;
import hello.core.Order.OrderServiceImple;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImple;

public class OrderApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImple();
        OrderService orderService = new OrderServiceImple();

        Long memberId = 1L;
        Member member = new Member(memberId, "seongjin", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId,"MacBook",10000);
        System.out.println("Order :" + order);
        System.out.println("최종가격 : "+order.calculatePrice());
    }
}
