package com.example.geektrust.repository;

import com.example.geektrust.enums.ProgrammeCategoryEnum;
import com.example.geektrust.models.DiscountCoupons;
import com.example.geektrust.models.Student;

public interface IStudentRepository {

    Integer getTotalProgrammeCount();

    Student getStudent();

    Integer getDegreeProgrammeCount();

    Double getDegreeProgrammeCost();

    Integer getCertificationProgrammeCount();

    Double getCertificationProgrammeCost();

    Integer getDiplomaProgrammeCount();

    Double getDiplomaProgrammeCost();

    void addProgrammesToCart(ProgrammeCategoryEnum programmes, Integer quantity);

    void addProMembershipPlan();

    void addDiscountCoupons(DiscountCoupons discountCoupons);

    Boolean containsDiscountCoupon(DiscountCoupons discountCoupon);

    Double getCertificationProgrammeDiscountAmount();

    Double getDegreeProgrammeDiscountAmount();

    Double getDiplomaProgrammeDiscountAmount();
}
