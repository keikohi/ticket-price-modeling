package com.example.demo.domain.model.types;

public enum StudentType {
    JUNIOR_HIGH_SCHOOL,
    HIGHSCHOOL,
    UNIVERSITY,
    COLLAGE,
    ELEMENTARY_SCHOOL,
    NON_STUDENET;
    
    public boolean isUniversityOrCollage() {
        return this.equals(UNIVERSITY) || this.equals(COLLAGE);
    }
    
    public boolean isJuniorOrHighSchool() {
        return this.equals(JUNIOR_HIGH_SCHOOL) || this.equals(HIGHSCHOOL);
    }
    
    public boolean isElementarySchool() {
        return this.equals(ELEMENTARY_SCHOOL);
    }
}
