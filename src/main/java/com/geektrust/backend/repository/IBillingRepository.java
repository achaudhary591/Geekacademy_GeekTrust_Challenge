package com.geektrust.backend.repository;

import com.geektrust.backend.models.DiscountCoupons;

public interface IBillingRepository {

    void setTotalProgramFees(Double totalProgramFees);
    //void setDiscount(Discount discount);
    Double getProMembershipDiscount();
    void setProMembershipDiscount(Double proMembershipDiscount);
    Double getProMembershipFee();
    void addEnrollmentFee();
    Double getEnrollmentFee();
    void setTotalAmount(Double totalAmount);
    Double getTotalProgramFees();
    //Discount getDiscount();
    Double getTotalAmount();
    DiscountCoupons getCouponDiscount();
    void setCouponDiscount(DiscountCoupons discountCoupons);
    Double getDiscountAmount();
    void setDiscountAmount(Double discountAmount);
    
}
