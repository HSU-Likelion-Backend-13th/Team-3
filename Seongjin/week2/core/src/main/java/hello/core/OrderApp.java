package hello.core;

import hello.core.Order.Order;
import hello.core.Order.OrderService;
import hello.core.Order.OrderServiceImple;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImple;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();

//        MemberService memberService = new MemberServiceImple();
//        OrderService orderService = new OrderServiceImple();

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ac.getBean("memberService",MemberServiceImple.class);
        OrderService orderService = ac.getBean("orderService",OrderServiceImple.class);

        Long memberId = 1L;
        Member member = new Member(memberId, "seongjin", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId,"MacBook",30000);
        System.out.println("Order :" + order);
        System.out.println("최종가격 : "+order.calculatePrice());
    }
}
