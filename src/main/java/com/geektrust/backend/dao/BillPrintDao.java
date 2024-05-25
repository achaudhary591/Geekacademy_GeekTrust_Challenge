package com.geektrust.backend.dao;

import com.geektrust.backend.models.DiscountCoupons;

public class BillPrintDao {
    private double totalProgrammeFee;
    private DiscountCoupons couponDiscountType;
    private double couponDiscountAmount;
    private double proMembershipDiscount;
    private double proMembershipFee;
    private double enrollmentFee;
    private double total;

    public BillPrintDao(double totalProgrammeFee, DiscountCoupons couponDiscountType, double couponDiscountAmount, double proMembershipDiscount, double proMembershipFee, double enrollmentFee, double total) {
        this.totalProgrammeFee = totalProgrammeFee;
        this.couponDiscountType = couponDiscountType;
        this.couponDiscountAmount = couponDiscountAmount;
        this.proMembershipDiscount = proMembershipDiscount;
        this.proMembershipFee = proMembershipFee;
        this.enrollmentFee = enrollmentFee;
        this.total = total;
    }

    public double getTotalProgrammeFee() {
        return totalProgrammeFee;
    }

    public double getCouponDiscountAmount() {
        return couponDiscountAmount;
    }

    public DiscountCoupons getCouponDiscountType() {
        return couponDiscountType;
    }

    public double getProMembershipDiscount() {
        return proMembershipDiscount;
    }

    public double getProMembershipFee() {
        return proMembershipFee;
    }

    public double getEnrollmentFee() {
        return enrollmentFee;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "SUB_TOTAL " + String.format("%.2f",totalProgrammeFee) +
            "\nCOUPON_DISCOUNT " + couponDiscountType + " " + String.format("%.2f",couponDiscountAmount) +
            "\nTOTAL_PRO_DISCOUNT " + String.format("%.2f",proMembershipDiscount) +
            "\nPRO_MEMBERSHIP_FEE " + String.format("%.2f",proMembershipFee) +
            "\nENROLLMENT_FEE " + String.format("%.2f",enrollmentFee) +
            "\nTOTAL " + String.format("%.2f",total);
    }
    
}
