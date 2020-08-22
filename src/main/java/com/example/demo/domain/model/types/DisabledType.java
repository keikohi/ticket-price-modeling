package com.example.demo.domain.model.types;

public enum DisabledType {
    DISABLED,
    NON_DISABLED;
    
    public boolean isDisabled() {
        return this.equals(DISABLED);
    }
}
