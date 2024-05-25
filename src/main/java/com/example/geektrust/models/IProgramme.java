package com.example.geektrust.models;

public interface IProgramme {

    Double getProgrammeFee();

    void addProgramme(Integer quantity);

    Integer getProgrammeCount();

    void addProMembershipDiscountCoupon();

    Double getProgrammeDiscountAmount();

}
