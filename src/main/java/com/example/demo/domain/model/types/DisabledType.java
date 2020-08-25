package com.example.demo.domain.model.types;

/* 障害者区分 */
public enum DisabledType {
    DISABLED,
    NON_DISABLED;
    
    public boolean isDisabled() {
        return this.equals(DISABLED);
    }
}
