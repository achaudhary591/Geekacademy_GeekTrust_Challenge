package com.geektrust.backend.service;

import com.geektrust.backend.models.DiscountCoupons;
import com.geektrust.backend.enums.ProgrammeCategoryEnum;
import com.geektrust.backend.exceptions.InvalidDiscountCouponException;
import com.geektrust.backend.exceptions.InvalidProgrammeCategoryException;
import com.geektrust.backend.repository.IStudentRepository;

public class StudentService implements IStudentService {

    private IStudentRepository studentRepository;

    public StudentService(IStudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void addProgrammes(String programCategoryStr, Integer quantity) {
        try{
            ProgrammeCategoryEnum programmeCategoryEnum = ProgrammeCategoryEnum.valueOf(programCategoryStr);
            studentRepository.addProgramsToCart(programmeCategoryEnum, quantity);
        } catch(IllegalArgumentException e) {
            throw new InvalidProgrammeCategoryException("Invalid program category: " + programCategoryStr);
        }

    }

    @Override
    public void setProMembershipPlan() {
        studentRepository.addProMembershipPlan();
    }

    @Override
    public void addDiscountCoupon(String discountCouponStr) {
        try{
            DiscountCoupons discountCoupon = DiscountCoupons.valueOf(discountCouponStr);
            studentRepository.addDiscountCoupons(discountCoupon);
        } catch(IllegalArgumentException e) {
            throw new InvalidDiscountCouponException("Invalid discount coupon: " + discountCouponStr);
        }
    }
    
}
