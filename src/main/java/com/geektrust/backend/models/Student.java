package com.geektrust.backend.models;

import com.geektrust.backend.helpers.Constants;

public class Student extends BaseModel {

    private Boolean isProMember;
    private Double proMembershipFee;
    private Double enrollmentFee;

    public Student() {
        isProMember = false;
        proMembershipFee = Constants.DOUBLE_INITIALIZE_VALUE;
        enrollmentFee = Constants.DOUBLE_INITIALIZE_VALUE;
    }

    public void addProMembershipPlan() {
        isProMember = true;
        proMembershipFee = Constants.PRO_MEMBERSHIP_FEE;
    }

    public void addEnrollmentFee() {
        enrollmentFee = Constants.ENROLLMENT_FEE;
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
