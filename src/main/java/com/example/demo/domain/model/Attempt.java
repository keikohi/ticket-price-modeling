package com.example.demo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class Attempt {
    private final Customer customer;
    private final Movie movie;
    private final Seat seat;
    private final Today today;
    
    
    public boolean isCinemaCitizen() {
        return this.customer.isCinemaCitizen();
    }
    
    public boolean isElderyCinemaCitizen() {
        return this.customer.isCinemaCitizen() && this.customer.isOver60();
    }
    
    public boolean isNormal() {
        return true;
    }
    
    public boolean isSenior() {
         return this.customer.isOver70();
    }
    
    public boolean isUniversityOrCollage() {
        return this.customer.isUniversityOrCollage();
    }
    
    public boolean isJuniorOrHighSchoolStudent() {
        return this.customer.isJuniorOrHighSchoolStudent();
    }

    public boolean isChild() {
        return this.customer.isChild();
    }

    public boolean isDisabled(){
        return this.customer.isDisabled();
    }

    public boolean isYoungDisabled(){
        return this.isDisabled() && this.customer.isYoung();
    }

    public boolean isBaby(){
        return this.customer.isBaby();
    }
}
