package com.geektrust.backend.models;

import com.geektrust.backend.enums.ProgrammeCategoryEnum;

public interface Programme {

    ProgrammeCategoryEnum getProgramme();

    Double getProgrammeFee();

    void addProgramme(Integer quantity);

    Integer getProgrammeCount();

    Double getProgrammeDiscount();

    void addProMembershipDiscountCoupon();

    Double getProgrammeDiscountAmount();

}
