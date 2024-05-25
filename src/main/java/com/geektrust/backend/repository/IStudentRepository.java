package com.geektrust.backend.repository;

import com.geektrust.backend.models.DiscountCoupons;
import com.geektrust.backend.enums.ProgrammeCategoryEnum;
import com.geektrust.backend.models.Student;
//import com.geektrust.backend.entities.DiscountCoupons;


public interface IStudentRepository {

    Integer getTotalProgrammeCount();
    Student getStudent();
    Integer getDegreeProgrammeCount();
    Double getDegreeProgrammeCost();
    Double getDegreeProgrammeDiscount();
    Integer getCertificationProgrammeCount();
    Double getCertificationProgrammeCost();
    Double getCertificationProgrammeDiscount();
    Integer getDiplomaProgrammeCount();
    Double getDiplomaProgrammeCost();
    Double getDiplomaProgrammeDiscount();
    void addProgramsToCart(ProgrammeCategoryEnum programmes, Integer quantity);
    void addProMembershipPlan();
    void addDiscountCoupons(DiscountCoupons discountCoupons);
    Boolean containsDiscountCoupon(DiscountCoupons discountCoupon);
    Double getCertificationProgrammeDiscountAmount();
    Double getDegreeProgrammeDiscountAmount();
    Double getDiplomaProgrammeDiscountAmount();
}
