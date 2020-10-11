package com.example.demo.domain.model;

import java.util.Comparator;
import java.util.Optional;

import com.example.demo.domain.model.rule.TermRule;
import com.example.demo.domain.model.rule.TicketRule;

import lombok.val;

/* チケット代の計算クラス */

public class Pricing {
    public static Price calculate(Attempt attempt) {
        val candidateTickets = TicketRule.matches(attempt.getCustomer());
        val termRule = TermRule.matches(attempt.getToday());
        val priceOptional = candidateTickets.stream()
            .map(ticket -> ticket.getPriceByTerm(termRule) )
            .min(Comparator.comparingInt(Price::getValue));
        return priceOptional.orElseThrow(IllegalArgumentException::new);
    }
}
