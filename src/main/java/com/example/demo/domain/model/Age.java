package com.example.demo.domain.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Age {
    private final int age;
    
    public boolean isBaby() {
        return this.age < 3; 
    }
    
    public boolean isLowerThan6() {
        return this.age < 6;
    }
    
    public boolean isOver60() {
        return this.age >= 60;
    }
    
    public boolean isOver70() {
        return this.age >= 70;
    }

}
