package com.example.demo.domain.model;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;

/* チケット購入日 */

@AllArgsConstructor(staticName = "of")
public class Today {
    private final LocalDateTime date;
    
    public boolean isMovieDay() {
        return this.date.getDayOfMonth() == 1;
    }
    
    public boolean isWeekEnd() {
        return this.date.getDayOfWeek().equals(DayOfWeek.SATURDAY) ||
                this.date.getDayOfWeek().equals(DayOfWeek.SUNDAY);
    }

    public boolean isWeekDay(){
        return !this.isWeekEnd();
    }
    
    public boolean isLateTime() {
        return this.date.getHour() >= 20;
    }

    public boolean isEarlyTime(){
        return !this.isLateTime();
    }
    
}
