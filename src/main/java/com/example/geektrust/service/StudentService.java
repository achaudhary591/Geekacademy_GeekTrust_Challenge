package com.example.geektrust.service;

import com.example.geektrust.models.DiscountCoupons;
import com.example.geektrust.enums.ProgrammeCategoryEnum;
import com.example.geektrust.exceptions.InvalidDiscountCouponException;
import com.example.geektrust.exceptions.InvalidProgrammeCategoryException;
import com.example.geektrust.repository.IStudentRepository;

public class StudentService implements IStudentService {

    private final IStudentRepository studentRepository;

    public StudentService(IStudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void addProgrammes(String programCategoryStr, Integer quantity) {
        try {
            ProgrammeCategoryEnum programmeCategoryEnum = ProgrammeCategoryEnum.valueOf(programCategoryStr);
            studentRepository.addProgrammesToCart(programmeCategoryEnum, quantity);
        } catch (IllegalArgumentException e) {
            throw new InvalidProgrammeCategoryException("Invalid program category: " + programCategoryStr);
        }

    }

    @Override
    public void setProMembershipPlan() {
        studentRepository.addProMembershipPlan();
    }

    @Override
    public void addDiscountCoupon(String discountCouponStr) {
        try {
            DiscountCoupons discountCoupon = DiscountCoupons.valueOf(discountCouponStr);
            studentRepository.addDiscountCoupons(discountCoupon);
        } catch (IllegalArgumentException e) {
            throw new InvalidDiscountCouponException("Invalid discount coupon: " + discountCouponStr);
        }
    }

}
