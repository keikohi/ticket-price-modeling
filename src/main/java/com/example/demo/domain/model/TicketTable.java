package com.example.demo.domain.model;


import com.example.demo.domain.model.rule.TermRule;

import lombok.Getter;

/* チケット料金表 */

@Getter
public enum TicketTable {
    /* シネマシティズン */
    CINEMA_CITIZEN(1000, 1000, 1300, 1000, 1100),
    /* シネマシティズン(60歳以上) */
    ELDER_CINEMA_CITIZEN(1000, 1000, 1000, 1000, 1000),
    /* 一般 */
    NORMAL(1800, 1300, 1800, 1300, 1100),
    /* シニア(70歳以上)*/
    SENIOR(1100, 1100, 1100, 1100, 1100),
    /* 学生（大・専） */
    UNIVERSITY_OR_COLLAGE(1500, 1300, 1500, 1300, 1100),
    /* 中・高校生 */
    JUNIOR_OR_HIGHSCHOOL(1000, 1000, 1000, 1000, 1000),
    /* 幼児（3才以上）・小学生 */
    CHILD(1000, 1000, 1000, 1000, 1000),
    /* 障がい者（学生以上） */
    DISABLED(1000, 1000, 1000, 1000, 1000),
    /* 障がい者（高校以下） */
    YOUNG_DISABLED(900, 900, 900, 900, 900),
    /* 2歳未満(無料) */
    BABY(0, 0, 0, 0, 0);
    
    private final Price earlyWeekdayPrice;
    private final Price lateWeekdayPrice;
    private final Price earlyWeekendPrice;
    private final Price lateWeekendPrice;
    private final Price movieDayPrice;
    
    TicketTable(int earlyWeekdayPrice,int lateWeekdayPrice,int earlyWeekendPrice,int lateWeekendPrice,int movieDayPrice) {
        this.earlyWeekdayPrice = Price.of(earlyWeekdayPrice);
        this.lateWeekdayPrice = Price.of(lateWeekdayPrice);
        this.earlyWeekendPrice = Price.of(earlyWeekendPrice);
        this.lateWeekendPrice = Price.of(lateWeekendPrice);
        this.movieDayPrice = Price.of(movieDayPrice);
    }
    
    public Price getPriceByTerm(TermRule termRule){
        switch (termRule) {
            case EARLY_WEEKDAY:
                return this.earlyWeekdayPrice;
            case LATE_WEEKDAY:
                return this.lateWeekdayPrice;
            case EARLY_WEEKEND:
                return this.earlyWeekendPrice;
            case LATE_WEEKEND:
                return this.lateWeekendPrice;
            case MOVIE_DAY:
                return this.movieDayPrice;
            default:
                throw new IllegalArgumentException("No such TermRule: " + termRule.toString());
        }
    }
}
