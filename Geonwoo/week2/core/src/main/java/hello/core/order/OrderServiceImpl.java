package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    // private final DiscountPolicy discountPolicy;
    // 누군가가 OrderServiceImpl에 대신 생성&주입 해줘야함
    private DiscountPolicy discountPolicy;

    /***
     * 사용자의 등급을 조회해서 각각 다른 할인 정책을 적용해야한다.
     * -> MemberRepository 에 접근해서 사용자 등급을 조회해야 함
     * @param memberId
     * @param itemName
     * @param itemPrice
     * @return
     */
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId); // 사용자 등급을 조회하기 위해

        /***
         * OrderService 입장에서는 할인에 대해 몰라도, DiscountPolicy 가 알아서 할인금액을 계산하고 값을 던져줌
         * -> SRP 준수
         */
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
