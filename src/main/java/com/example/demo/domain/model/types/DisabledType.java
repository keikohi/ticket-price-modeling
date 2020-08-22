package com.example.demo.domain.model.types;

public enum DisabledType {
    Disabled,
    NonDisabled;
    
    public boolean isDisabled() {
        return this.equals(Disabled);
    }
}
