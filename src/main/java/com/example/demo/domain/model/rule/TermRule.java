package com.example.demo.domain.model.rule;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import com.example.demo.domain.model.Today;

import lombok.ToString;

/* 上映のタームとその条件 */

@ToString
public enum TermRule {
    /* 映画の日 */
    MOVIE_DAY(Today::isMovieDay),
    /* 平日 (～20:00) */
    EARLY_WEEKDAY(today -> today.isEarlyTime() && today.isWeekDay()),
    /* 平日 (20:00～) */
    LATE_WEEKDAY(today -> today.isLateTime() && today.isWeekDay()),
    /* 土日 (～20:00) */
    EARLY_WEEKEND(today -> today.isEarlyTime() && today.isWeekEnd()),
    /* 土日 (20:00～) */
    LATE_WEEKEND(today -> today.isLateTime() && today.isWeekEnd());

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