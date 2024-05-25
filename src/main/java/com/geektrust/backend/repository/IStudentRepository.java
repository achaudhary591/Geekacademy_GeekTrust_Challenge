package com.geektrust.backend.repository;

import com.geektrust.backend.enums.ProgrammeCategoryEnum;
import com.geektrust.backend.models.DiscountCoupons;
import com.geektrust.backend.models.Student;

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
