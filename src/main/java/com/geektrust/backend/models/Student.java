package com.geektrust.backend.models;

import com.geektrust.backend.utils.GeekdemyConstants;

public class Student extends BaseModel{

    private Boolean isProMember;
    private Double proMembershipFee;
    private Double enrollmentFee;

    public Student() {
        isProMember = false;
        proMembershipFee = 0.0;
        enrollmentFee = 0.0;
    }

    public void addProMembershipPlan() {
        isProMember = true;
        proMembershipFee = GeekdemyConstants.PRO_MEMBERSHIP_FEE;
    }

    public void addEnrollmentFee() {
        enrollmentFee = GeekdemyConstants.ENROLLMENT_FEE;
    }

    public Boolean getProMembershipStatus() {
        return isProMember;
    }

    public Double getProMembershipFee() {
        return proMembershipFee;
    }

    public Double getEnrollmentFee() {
        return enrollmentFee;
    }
    
}
