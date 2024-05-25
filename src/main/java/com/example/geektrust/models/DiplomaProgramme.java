package com.example.geektrust.models;

import com.example.geektrust.enums.ProgrammeCategoryEnum;
import com.example.geektrust.helpers.Constants;

public class DiplomaProgramme extends BaseModel implements Programme {

    private final ProgrammeCategoryEnum programmeCategoryEnum;
    private final Double programmeDiscount;
    private Double programmeFees;
    private Integer count;
    private Double programmeDiscountAmount;

    public DiplomaProgramme() {
        programmeCategoryEnum = ProgrammeCategoryEnum.DIPLOMA;
        programmeFees = Constants.DIPLOMA_FEES;
        count = Constants.INTEGER_INITIALIZE_VALUE;
        programmeDiscount = Constants.DIPLOMA_DISCOUNT;
        programmeDiscountAmount = Constants.DOUBLE_INITIALIZE_VALUE;
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
        programmeFees = programmeFees * (1 - programmeDiscount);
    }

    @Override
    public Double getProgrammeDiscountAmount() {
        return programmeDiscountAmount;
    }

}
