package hello.core.beanfinder;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class ApplicationContextExtendsFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("As to inquiry type of superclass, occurs error if there are over 2 child")
    void findBeanByParentTypeDuplicate(){

        Assertions.assertThrows(NoUniqueBeanDefinitionException.class,
                ()-> ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("As to inquiry type of superclass findBeanByParentName")
    void findBeanByParentName(){

        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);

        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("As to inquiry type of superclass findBeanByParentSpecificType")
    void findBeanByParentSpecificType(){

        RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);

        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("As to inquiry type of superclass findAllBeanByParentType")
    void findAllBeanByParentType(){

        Map<String, DiscountPolicy> bean = ac.getBeansOfType(DiscountPolicy.class);

        for(String key : bean.keySet()){
            Object value = bean.get(key);
            System.out.println("key = " + key + " Value = " + value);
        }

        assertThat(bean.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("As to inquiry type of superclass findAllBeanByParentType")
    void findAllBeanByObjectType(){

        Map<String, Object> bean = ac.getBeansOfType(Object.class);

        for(String key : bean.keySet()){
            Object value = bean.get(key);
            System.out.println("key = " + key + " Value = " + value);
        }

        //assertThat(bean.size()).isEqualTo(2);
    }


    @Configuration
    static class TestConfig{

        @Bean
        public DiscountPolicy rateDiscountPolicy(){
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy(){
            return new FixDiscountPolicy();
        }

    }
}
