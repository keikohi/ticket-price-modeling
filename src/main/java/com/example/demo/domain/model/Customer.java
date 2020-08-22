package com.example.demo.domain.model;

import com.example.demo.domain.model.types.DisabledType;
import com.example.demo.domain.model.types.MembershipType;
import com.example.demo.domain.model.types.StudentType;

import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "of")
public class Customer {
    private final Age age;
    private final MembershipType membershipType;
    private final StudentType studentType;
    private final DisabledType disabledType;
    
    
    boolean isCinemaCitizen() {
        return this.membershipType.equals(MembershipType.CinemaCitizen);
    }
    
    boolean isOver60() {
        return this.age.isOver60();
    }
    
    boolean isOver70() {
        return this.age.isOver70();
    }
    
    boolean isBaby() {
        return this.age.isBaby();
    }
    
    boolean isUniversityOrCollage() {
        return this.studentType.equals(StudentType.University) ||
                this.studentType.equals(StudentType.Collage);
    }
    
    boolean isJuniorOrHighSchoolStudent() {
        return this.studentType.equals(StudentType.JuniorHighSchool) ||
                this.studentType.equals(StudentType.HighSchool);
    }
    
    boolean isChild() {
        return (this.age.isLowerThan6() && !this.age.isBaby() ) || 
                this.studentType.equals(StudentType.ElementarySchool);
    }
    
    boolean isDisabled() {
        return this.disabledType.equals(DisabledType.Disabled);
    }

    boolean isYoung(){
        return isChild() || isJuniorOrHighSchoolStudent() ;
    }
    
}
