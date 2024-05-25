package com.geektrust.backend.repository;

import com.geektrust.backend.models.DiscountCoupons;
import com.geektrust.backend.models.Student;

public class BillingRepository implements IBillingRepository {

    private Student student;
    private Double totalProgramFees;
    //private Discount discount;
    private DiscountCoupons discountCoupons;
    private Double discountAmount;
    private Double proMembershipDiscount;
    private Double totalAmount;


    public BillingRepository(Student student) {
        this.student = student;
        totalProgramFees = 0.00;
        proMembershipDiscount = 0.00;
        totalAmount = 0.00;
    }


    @Override
    public void setTotalProgramFees(Double totalProgramFees) {
        // TODO Auto-generated method stub
        this.totalProgramFees = totalProgramFees;
        
    }

    @Override
    public void setProMembershipDiscount(Double proMembershipDiscount) {
        // TODO Auto-generated method stub
        this.proMembershipDiscount = proMembershipDiscount;
    }

    @Override
    public void addEnrollmentFee() {
        // TODO Auto-generated method stub
        student.addEnrollmentFee();
        
    }

    @Override
    public void setTotalAmount(Double totalAmount) {
        // TODO Auto-generated method stub
        this.totalAmount = totalAmount;
        
    }


    @Override
    public Double getTotalProgramFees() {
        // TODO Auto-generated method stub
        return totalProgramFees;
    }


    // @Override
    // public Discount getDiscount() {
    //     // TODO Auto-generated method stub
    //     return discount;
    // }


    @Override
    public Double getProMembershipDiscount() {
        // TODO Auto-generated method stub
        return proMembershipDiscount;
    }


    @Override
    public Double getProMembershipFee() {
        // TODO Auto-generated method stub
        return student.getProMembershipFee();
    }


    @Override
    public Double getEnrollmentFee() {
        // TODO Auto-generated method stub
        return student.getEnrollmentFee();
    }


    @Override
    public Double getTotalAmount() {
        // TODO Auto-generated method stub
        return totalAmount;
    }


    @Override
    public Double getDiscountAmount() {
        // TODO Auto-generated method stub
        return discountAmount;

    }


    @Override
    public void setDiscountAmount(Double discountAmount) {
        // TODO Auto-generated method stub
        this.discountAmount = discountAmount;
        
    }


    @Override
    public DiscountCoupons getCouponDiscount() {
        // TODO Auto-generated method stub
        return discountCoupons;
    }


    @Override
    public void setCouponDiscount(DiscountCoupons discountCoupons) {
        // TODO Auto-generated method stub
        this.discountCoupons = discountCoupons;
        
    }

    
    
}
