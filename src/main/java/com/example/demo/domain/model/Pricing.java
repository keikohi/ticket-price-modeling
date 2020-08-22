package com.example.demo.domain.model;

import java.util.Comparator;
import java.util.Optional;

import lombok.val;

public class Pricing {
    
    public static Price calculate(Attempt attempt) {
        val termRule = TermRule.matches(attempt.getToday());
        val candidates = TicketRule.matches(attempt.getCustomer());
        val priceOptional = candidates.stream()
            .map(c -> c.getPriceByTerm(termRule) )
            .min(Comparator.comparingInt(Price::getValue));
        return Optional.ofNullable(priceOptional.get()).orElseThrow(IllegalArgumentException::new);
    }
    
}
