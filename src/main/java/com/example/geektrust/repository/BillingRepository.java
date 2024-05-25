package com.example.geektrust.repository;

import com.example.geektrust.helpers.Constants;
import com.example.geektrust.models.DiscountCoupons;
import com.example.geektrust.models.Student;

public class BillingRepository implements IBillingRepository {

    private Student student;
    private Double totalProgrammeFees;
    private DiscountCoupons discountCoupons;
    private Double discountAmount;
    private Double proMembershipDiscount;
    private Double totalAmount;


    public BillingRepository(Student student) {
        this.student = student;
        totalProgrammeFees = Constants.DOUBLE_INITIALIZE_VALUE;
        proMembershipDiscount = Constants.DOUBLE_INITIALIZE_VALUE;
        totalAmount = Constants.DOUBLE_INITIALIZE_VALUE;
    }

    @Override
    public void addEnrollmentFee() {

        student.addEnrollmentFee();

    }

    @Override
    public Double getTotalProgrammeFees() {

        return totalProgrammeFees;
    }

    @Override
    public void setTotalProgrammeFees(Double totalProgrammeFees) {

        this.totalProgrammeFees = totalProgrammeFees;

    }

    @Override
    public Double getProMembershipDiscount() {

        return proMembershipDiscount;
    }

    @Override
    public void setProMembershipDiscount(Double proMembershipDiscount) {

        this.proMembershipDiscount = proMembershipDiscount;
    }


    // @Override
    // public Discount getDiscount() {
    //     
    //     return discount;
    // }

    @Override
    public Double getProMembershipFee() {

        return student.getProMembershipFee();
    }

    @Override
    public Double getEnrollmentFee() {

        return student.getEnrollmentFee();
    }

    @Override
    public Double getTotalAmount() {

        return totalAmount;
    }

    @Override
    public void setTotalAmount(Double totalAmount) {

        this.totalAmount = totalAmount;

    }

    @Override
    public Double getDiscountAmount() {

        return discountAmount;

    }


    @Override
    public void setDiscountAmount(Double discountAmount) {

        this.discountAmount = discountAmount;

    }


    @Override
    public DiscountCoupons getCouponDiscount() {

        return discountCoupons;
    }


    @Override
    public void setCouponDiscount(DiscountCoupons discountCoupons) {

        this.discountCoupons = discountCoupons;

    }


}
