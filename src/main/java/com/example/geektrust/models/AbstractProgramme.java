package com.example.geektrust.models;

import com.example.geektrust.enums.ProgrammeCategoryEnum;
import com.example.geektrust.helpers.Constants;

public abstract class AbstractProgramme extends BaseModel implements Programme {

    protected final ProgrammeCategoryEnum programmeCategoryEnum;
    protected final Double programmeDiscount;
    protected Double programmeFees;
    protected Integer count;
    protected Double programmeDiscountAmount;

    protected AbstractProgramme(ProgrammeCategoryEnum categoryEnum, Double fees, Double discount) {
        this.programmeCategoryEnum = categoryEnum;
        this.programmeFees = fees;
        this.programmeDiscount = discount;
        this.count = Constants.INTEGER_INITIALIZE_VALUE;
        this.programmeDiscountAmount = Constants.DOUBLE_INITIALIZE_VALUE;
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

