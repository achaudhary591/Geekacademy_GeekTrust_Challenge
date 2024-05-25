package com.example.geektrust.models;

import com.example.geektrust.enums.ProgrammeCategoryEnum;
import com.example.geektrust.helpers.Constants;

public class DiplomaIProgramme extends AbstractProgramme implements IProgramme {

    public DiplomaIProgramme() {
        super(ProgrammeCategoryEnum.DIPLOMA, Constants.DIPLOMA_FEES, Constants.DIPLOMA_DISCOUNT);
    }


    @Override
    public ProgrammeCategoryEnum getProgramme() {
        return programmeCategoryEnum;
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
    public Double getProgrammeDiscount() {
        return programmeDiscount;
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
