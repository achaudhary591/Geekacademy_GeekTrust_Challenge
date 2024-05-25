package com.example.geektrust.models;

import com.example.geektrust.enums.ProgrammeCategoryEnum;
import com.example.geektrust.helpers.Constants;

public class DegreeProgramme extends AbstractProgramme implements IProgramme {
    public DegreeProgramme() {
        super(ProgrammeCategoryEnum.DEGREE, Constants.DEGREE_FEES, Constants.DEGREE_DISCOUNT);
    }


    @Override
    public Double getProgrammeFee() {
        return programmeFees;
    }

    @Override
    public void addProgramme(Integer quantity) {
        count += quantity;
    }

    @Override
    public Integer getProgrammeCount() {
        return count;
    }

    @Override
    public void addProMembershipDiscountCoupon() {
        programmeDiscountAmount = programmeFees * programmeDiscount;
        programmeFees *= (1 - programmeDiscount);
    }

    @Override
    public Double getProgrammeDiscountAmount() {
        return programmeDiscountAmount;
    }
}