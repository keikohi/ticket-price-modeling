package com.example.demo.domain.model.types;

public enum MembershipType {
    CinemaCitizen,
    NonMember;
    
    public boolean isCinemaCitizen() {
        return this.equals(CinemaCitizen);
    }
}
