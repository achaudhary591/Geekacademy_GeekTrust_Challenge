package com.geektrust.backend.repository;

import com.geektrust.backend.models.DiscountCoupons;

public interface IBillingRepository {

    Double getProMembershipDiscount();

    void setProMembershipDiscount(Double proMembershipDiscount);

    Double getProMembershipFee();

    void addEnrollmentFee();

    Double getEnrollmentFee();

    Double getTotalProgrammeFees();

    void setTotalProgrammeFees(Double totalProgrammeFees);

    Double getTotalAmount();

    void setTotalAmount(Double totalAmount);

    DiscountCoupons getCouponDiscount();

    void setCouponDiscount(DiscountCoupons discountCoupons);

    Double getDiscountAmount();

    void setDiscountAmount(Double discountAmount);

}
