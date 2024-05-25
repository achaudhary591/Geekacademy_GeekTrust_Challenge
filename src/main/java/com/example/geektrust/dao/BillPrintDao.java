package com.example.geektrust.dao;

import com.example.geektrust.models.DiscountCoupons;

public class BillPrintDao {
    private final double totalProgrammeFee;
    private final DiscountCoupons couponDiscountType;
    private final double couponDiscountAmount;
    private final double proMembershipDiscount;
    private final double proMembershipFee;
    private final double enrollmentFee;
    private final double totalBillValue;

    public BillPrintDao(double totalProgrammeFee, DiscountCoupons couponDiscountType, double couponDiscountAmount, double proMembershipDiscount, double proMembershipFee, double enrollmentFee, double totalBillValue) {
        this.totalProgrammeFee = totalProgrammeFee;
        this.couponDiscountType = couponDiscountType;
        this.couponDiscountAmount = couponDiscountAmount;
        this.proMembershipDiscount = proMembershipDiscount;
        this.proMembershipFee = proMembershipFee;
        this.enrollmentFee = enrollmentFee;
        this.totalBillValue = totalBillValue;
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

    public double getTotalBillValue() {
        return totalBillValue;
    }

    @Override
    public String toString() {

        return "SUB_TOTAL " + String.format("%.2f", totalProgrammeFee) +
                "\nCOUPON_DISCOUNT " + couponDiscountType + " " + String.format("%.2f", couponDiscountAmount) +
                "\nTOTAL_PRO_DISCOUNT " + String.format("%.2f", proMembershipDiscount) +
                "\nPRO_MEMBERSHIP_FEE " + String.format("%.2f", proMembershipFee) +
                "\nENROLLMENT_FEE " + String.format("%.2f", enrollmentFee) +
                "\nTOTAL " + String.format("%.2f", totalBillValue);
    }

}
