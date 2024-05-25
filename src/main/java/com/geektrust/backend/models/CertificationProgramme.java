package com.geektrust.backend.models;

import com.geektrust.backend.enums.ProgrammeCategoryEnum;
import com.geektrust.backend.utils.GeekdemyConstants;

public class CertificationProgramme extends BaseModel implements Programme{

    private final ProgrammeCategoryEnum programmeCategoryEnum;
    private Double programFees;
    private Integer count;
    private final Double programDiscount;

    private Double programDiscountAmount;

    public CertificationProgramme() {
        programmeCategoryEnum = ProgrammeCategoryEnum.CERTIFICATION;
        programFees = GeekdemyConstants.CERTIFICATION_FEES;
        count = 0;
        programDiscount = GeekdemyConstants.CERTIFICATION_DISCOUNT;
        programDiscountAmount = 0.0;
    }

    @Override
    public ProgrammeCategoryEnum getProgram() {
        // TODO Auto-generated method stub
        return programmeCategoryEnum;
    }

    @Override
    public Double getProgrammeFee() {
        // TODO Auto-generated method stub
        return programFees;
    }

    @Override
    public void addProgramme(Integer quantity) {
        // TODO Auto-generated method stub
        count = count + quantity;
    }

    @Override
    public Integer getProgrammeCount() {
        // TODO Auto-generated method stub
        return count;
    }

    @Override
    public Double getProgrammeDiscount() {
        // TODO Auto-generated method stub
        return programDiscount;
    }

    @Override
    public void addProMembershipDiscountCoupon() {
        // TODO Auto-generated method stub
        programDiscountAmount = programFees * programDiscount;
        programFees = programFees * (1 - programDiscount);
    }

    @Override
    public Double getProgrammeDiscountAmount() {
        // TODO Auto-generated method stub
        return programDiscountAmount;
    }

    
}
