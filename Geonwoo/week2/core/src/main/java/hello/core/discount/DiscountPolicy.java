package hello.core.discount;

import hello.core.member.Member;

public interface DiscountPolicy {

    /***
     * @param member
     * @return 할인 대상 금액(등급별 할인 금액)
     */
    int discount(Member member, int price);
}
