package com.example.demo.domain.model;

import lombok.AllArgsConstructor;

/* カスタマーの年齢 */

@AllArgsConstructor(staticName = "of")
public class Age {
    private final int value;
    
    public boolean isBaby() {
        return this.value < 3; 
    }
    
    public boolean isLowerThan6() {
        return this.value < 6;
    }
    
    public boolean isOver60() {
        return this.value >= 60;
    }
    
    public boolean isOver70() {
        return this.value >= 70;
    }

}
