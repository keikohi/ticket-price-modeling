package com.example.demo.domain.model.types;

public enum MembershipType {
    CINEMA_CITIZEN,
    NON_MEMBER;
    
    public boolean isCinemaCitizen() {
        return this.equals(CINEMA_CITIZEN);
    }
}
