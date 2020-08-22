package com.example.demo.domain.model;

import java.rmi.UnexpectedException;

import lombok.Getter;

@Getter
public enum TicketTable {
    CINEMA_CITIZEN(1000, 1000, 1300, 1000, 1100),
    ELDER_CINEMA_CITIZEN(1000, 1000, 1000, 1000, 1000),
    
    NORMAL(1800, 1300, 1800, 1300, 1100),
    
    SENIOR(1100, 1100, 1100, 1100, 1100),
    
    UNIVERSITY_OR_COLLAGE(1500, 1300, 1500, 1300, 1100),
    JUNIOR_OR_HIGHSCHOOL(1000, 1000, 1000, 1000, 1000),
    CHILD(1000, 1000, 1000, 1000, 1000),
    
    DISABLED(1000, 1000, 1000, 1000, 1000),
    YOUNG_DISABLED(900, 900, 900, 900, 900),
    
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
