package com.example.demo.domain.model;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public enum TermRule {
    EARLY_WEEKDAY(today -> today.isEarlyTime() && today.isWeekDay()),
    LATE_WEEKDAY(today -> today.isLateTime() && today.isWeekDay()),
    EARLY_WEEKEND(today -> today.isEarlyTime() && today.isWeekEnd()),
    LATE_WEEKEND(today -> today.isLateTime() && today.isWeekDay()),
    MOVIE_DAY(Today::isMovieDay);

    private final Predicate<Today> predicate;

    TermRule(Predicate<Today> predicate){
        this.predicate = predicate;
    }

    public static TermRule matches(Today today){
        Optional<TermRule> optional =  Stream.of(TermRule.values())
            .filter(t -> t.test(today))
            .findFirst();
            //TODO optionalの見直し
            return Optional.ofNullable(optional.get()).orElseThrow(IllegalArgumentException::new);
    }

    private boolean test(Today today){
        return this.predicate.test(today);
    }
    
}