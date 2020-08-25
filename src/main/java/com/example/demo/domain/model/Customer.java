package com.example.demo.domain.model;

import com.example.demo.domain.model.types.DisabledType;
import com.example.demo.domain.model.types.MembershipType;
import com.example.demo.domain.model.types.StudentType;

import lombok.AllArgsConstructor;

/* カスタマー */

@AllArgsConstructor(staticName = "of")
public class Customer {
    private final Age age;
    private final MembershipType membershipType;
    private final StudentType studentType;
    private final DisabledType disabledType;
    
    public boolean isCinemaCitizen() {
        return this.membershipType.equals(MembershipType.CINEMA_CITIZEN);
    }
    
    public boolean isOver60() {
        return this.age.isOver60();
    }
    
    public boolean isOver70() {
        return this.age.isOver70();
    }
    
    public boolean isBaby() {
        return this.age.isBaby();
    }
    
    public boolean isUniversityOrCollage() {
        return this.studentType.equals(StudentType.UNIVERSITY) ||
                this.studentType.equals(StudentType.COLLAGE);
    }
    
    public boolean isJuniorOrHighSchoolStudent() {
        return this.studentType.equals(StudentType.JUNIOR_HIGH_SCHOOL) ||
                this.studentType.equals(StudentType.HIGHSCHOOL);
    }
    
    public boolean isChild() {
        return (this.age.isLowerThan6() && !this.age.isBaby() ) || 
                this.studentType.equals(StudentType.ELEMENTARY_SCHOOL);
    }
    
    public boolean isDisabled() {
        return this.disabledType.equals(DisabledType.DISABLED);
    }

    public boolean isYoung(){
        return isChild() || isJuniorOrHighSchoolStudent() ;
    }
    
}
