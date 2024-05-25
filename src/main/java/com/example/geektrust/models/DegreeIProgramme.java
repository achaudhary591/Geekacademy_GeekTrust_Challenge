package com.example.geektrust.models;

import com.example.geektrust.enums.ProgrammeCategoryEnum;
import com.example.geektrust.helpers.Constants;

public class DegreeIProgramme extends AbstractProgramme implements IProgramme {
    public DegreeIProgramme() {
        super(ProgrammeCategoryEnum.DEGREE, Constants.DEGREE_FEES, Constants.DEGREE_DISCOUNT);
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