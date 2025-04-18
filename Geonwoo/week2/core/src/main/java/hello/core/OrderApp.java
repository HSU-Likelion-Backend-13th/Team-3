package hello.core;


import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {
        /*
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();
         */
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ac.getBean(MemberService.class);
        OrderService orderService = ac.getBean(OrderService.class);


        Long memberId = 1L;
        Member member = new Member(memberId, "dgw", Grade.BASIC);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "Mac mini", 10000);
        System.out.println("Order: " + order);
        System.out.println("Final price: " + order.calculatePrice());
    }
}
