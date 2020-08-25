package com.example.demo.domain.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/* 料金 */

@Getter
@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode
public class Price {
    private final int value;
}
