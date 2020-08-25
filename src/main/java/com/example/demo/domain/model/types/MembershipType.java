package com.example.demo.domain.model.types;

/* シネマシティズン区分 */
public enum MembershipType {
    CINEMA_CITIZEN,
    NON_MEMBER;
    
    public boolean isCinemaCitizen() {
        return this.equals(CINEMA_CITIZEN);
    }
}
