package com.example.geektrust.models;

import com.example.geektrust.enums.ProgrammeCategoryEnum;
import com.example.geektrust.helpers.Constants;

public abstract class AbstractProgramme extends BaseModel {

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


    public abstract Double getProgrammeFee();


    public abstract void addProgramme(Integer quantity);


    public abstract Integer getProgrammeCount();


    public abstract void addProMembershipDiscountCoupon();


    public abstract Double getProgrammeDiscountAmount();
}

