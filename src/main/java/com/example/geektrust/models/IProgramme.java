package com.example.geektrust.models;

import com.example.geektrust.enums.ProgrammeCategoryEnum;

public interface IProgramme {

    ProgrammeCategoryEnum getProgramme();

    Double getProgrammeFee();

    void addProgramme(Integer quantity);

    Integer getProgrammeCount();

    Double getProgrammeDiscount();

    void addProMembershipDiscountCoupon();

    Double getProgrammeDiscountAmount();

}
