package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {
    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("there are policies to get discount for VIP")// launched from Junit5
    void vip_o(){
        Member member = new Member(1L, "memberA", Grade.VIP);
        int discount = discountPolicy.discount(member, 10000);

        assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("there are policies not to get discount for BASIC")// launched from Junit5
    void vip_x(){
        Member member = new Member(2L, "memberA", Grade.BASIC);
        int discount = discountPolicy.discount(member, 10000);

        assertThat(discount).isEqualTo(0);
    }
}