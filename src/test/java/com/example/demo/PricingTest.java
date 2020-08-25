package com.example.demo;

import com.example.demo.domain.model.Age;
import com.example.demo.domain.model.Attempt;
import com.example.demo.domain.model.Customer;
import com.example.demo.domain.model.Movie;
import com.example.demo.domain.model.Price;
import com.example.demo.domain.model.Pricing;
import com.example.demo.domain.model.Seat;
import com.example.demo.domain.model.Today;
import com.example.demo.domain.model.types.DisabledType;
import com.example.demo.domain.model.types.MembershipType;
import com.example.demo.domain.model.types.StudentType;


import java.time.LocalDateTime;
import java.time.Month;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import lombok.val;

public class PricingTest {
    
    @Test
    public void テスト(){

        /* 赤ちゃん */
        {
            val customer = Customer.of(Age.of(1), MembershipType.NON_MEMBER, StudentType.NON_STUDENET, DisabledType.NON_DISABLED);
            val attempt = Attempt.of(customer, Movie.of("映画"), Seat.of("お父さん膝"), Today.of(LocalDateTime.of(2020, Month.JUNE, 1, 12, 0)));

            val actual = Pricing.calculate(attempt);
            assertEquals(Price.of(0), actual);
        }
        
        /* シネマシティズン 高校生 平日 日中*/
        {
            val customer = Customer.of(Age.of(16), MembershipType.CINEMA_CITIZEN, StudentType.HIGHSCHOOL, DisabledType.NON_DISABLED);
            val attempt = Attempt.of(customer, Movie.of(""), Seat.of(""), Today.of(LocalDateTime.of(2020, Month.JUNE, 2, 12, 0)));

            val actual = Pricing.calculate(attempt);
            assertEquals(1000, actual.getValue());
        }

        /*  一般 土日 夜*/
        {
            val customer = Customer.of(Age.of(28), MembershipType.NON_MEMBER, StudentType.NON_STUDENET, DisabledType.NON_DISABLED);
            val attempt = Attempt.of(customer, Movie.of(""), Seat.of(""), Today.of(LocalDateTime.of(2020, Month.JUNE, 6, 20, 0)));

            val actual = Pricing.calculate(attempt);
            assertEquals( 1300, actual.getValue());
        }

        /*  シネマシティズン　一般 土日 夜*/
        {
            val customer = Customer.of(Age.of(28), MembershipType.CINEMA_CITIZEN, StudentType.NON_STUDENET, DisabledType.NON_DISABLED);
            val attempt = Attempt.of(customer, Movie.of(""), Seat.of(""), Today.of(LocalDateTime.of(2020, Month.JUNE, 6, 20, 0)));

            val actual = Pricing.calculate(attempt);
            assertEquals( 1000, actual.getValue());
        }

        /*  一般 シネマの日 夜*/
        {
            val customer = Customer.of(Age.of(28), MembershipType.NON_MEMBER, StudentType.NON_STUDENET, DisabledType.NON_DISABLED);
            val attempt = Attempt.of(customer, Movie.of(""), Seat.of(""), Today.of(LocalDateTime.of(2020, Month.JUNE, 1, 20, 0)));
            val actual = Pricing.calculate(attempt);
            assertEquals( 1100, actual.getValue());
        }

        /* 障害者 シニア シネマシティズン */
        {
            val customer = Customer.of(Age.of(72), MembershipType.CINEMA_CITIZEN, StudentType.NON_STUDENET, DisabledType.DISABLED);
            val attempt = Attempt.of(customer, Movie.of(""), Seat.of(""), Today.of(LocalDateTime.of(2020, Month.JUNE, 2, 12, 0)));

            val actual = Pricing.calculate(attempt);
            assertEquals( 1000, actual.getValue());
        }

    }
}