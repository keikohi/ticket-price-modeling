package com.example.demo.domain.model.types;

public enum StudentType {
    JuniorHighSchool,
    HighSchool,
    University,
    Collage,
    ElementarySchool;
    
    public boolean isUniversityOrCollage() {
        return this.equals(University) || this.equals(Collage);
    }
    
    public boolean isJuniorOrHighSchool() {
        return this.equals(JuniorHighSchool) || this.equals(HighSchool);
    }
    
    public boolean isElementarySchool() {
        return this.equals(ElementarySchool);
    }
}
