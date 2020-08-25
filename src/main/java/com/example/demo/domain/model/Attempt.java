package com.example.demo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/* 購入試行 */

@Getter
@AllArgsConstructor(staticName = "of")
public class Attempt {
    private final Customer customer;
    private final Movie movie;
    private final Seat seat;
    private final Today today;
    
}
