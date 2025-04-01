package hello.core.Order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImple implements OrderService {
    /**
    * 사용자의 등급을 조회하여 사용자마다 다른 할인 정책을 적용해야 함.
     *  -> MemberRepository에 접근하여 사용자 등급을 조회해야 함.
    */
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    //누군가가 OrderServiceImpl에 DiscountPolicy의 구현 객체를 대신 생성하고 주입해줘야함. ->gradle에서..?
    private DiscountPolicy discountPolicy;

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId); //사용자 등급을 조회하기 위해

        /**
         * OrderService 입장에서는 할인에 대해 몰라도, DiscountPolicy가 할인금액을 알아서 계산하고 값 던져줌.
         * ->SRP (단일 책임 원칙 잘 준수)
         */
        //여기서 조회뿐만아니라 할인가까지 측정하는 것은 적절하지 못함.
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
