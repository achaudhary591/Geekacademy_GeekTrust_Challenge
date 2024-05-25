package com.geektrust.backend.models;

import com.geektrust.backend.enums.ProgrammeCategoryEnum;
import com.geektrust.backend.helpers.Constants;

public class CertificationProgramme extends BaseModel implements Programme {

    private final ProgrammeCategoryEnum programmeCategoryEnum;
    private final Double programmeDiscount;
    private Double programmeFees;
    private Integer count;
    private Double programmeDiscountAmount;

    public CertificationProgramme() {
        programmeCategoryEnum = ProgrammeCategoryEnum.CERTIFICATION;
        programmeFees = Constants.CERTIFICATION_FEES;
        count = Constants.INTEGER_INITIALIZE_VALUE;
        programmeDiscount = Constants.CERTIFICATION_DISCOUNT;
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
        count = count + quantity;
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
