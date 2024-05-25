package com.example.geektrust.repository;

import com.example.geektrust.enums.ProgrammeCategoryEnum;
import com.example.geektrust.models.*;
import com.example.geektrust.models.*;
import com.example.geektrust.models.*;

import java.util.ArrayList;
import java.util.List;


public class StudentRepository implements IStudentRepository {

    private final Student student;
    private Programme certificationProgramme;
    private Programme degreeProgramme;
    private Programme diplomaProgramme;
    private List<DiscountCoupons> discountCoupons;


    public StudentRepository(Student student) {
        this.student = student;
        certificationProgramme = new CertificationProgramme();
        degreeProgramme = new DegreeProgramme();
        diplomaProgramme = new DiplomaProgramme();
        discountCoupons = new ArrayList<>();
    }

    public StudentRepository(Student student, CertificationProgramme certificationProgramme, DegreeProgramme degreeProgramme, DiplomaProgramme diplomaProgramme) {
        this(student);
        this.certificationProgramme = certificationProgramme;
        this.degreeProgramme = degreeProgramme;
        this.diplomaProgramme = diplomaProgramme;
        discountCoupons = new ArrayList<>();
    }


    public Student getStudent() {
        return student;
    }

    public Integer getTotalProgrammeCount() {
        return certificationProgramme.getProgrammeCount() + degreeProgramme.getProgrammeCount() + diplomaProgramme.getProgrammeCount();
    }

    @Override
    public void addProgrammesToCart(ProgrammeCategoryEnum programmeCategoryEnum, Integer quantity) {
        if (programmeCategoryEnum == ProgrammeCategoryEnum.CERTIFICATION) {
            certificationProgramme.addProgramme(quantity);
        } else if (programmeCategoryEnum == ProgrammeCategoryEnum.DEGREE) {
            degreeProgramme.addProgramme(quantity);
        } else {
            diplomaProgramme.addProgramme(quantity);
        }
    }

    @Override
    public void addProMembershipPlan() {
        student.addProMembershipPlan();
        certificationProgramme.addProMembershipDiscountCoupon();
        degreeProgramme.addProMembershipDiscountCoupon();
        diplomaProgramme.addProMembershipDiscountCoupon();

    }

    @Override
    public Integer getDegreeProgrammeCount() {

        return degreeProgramme.getProgrammeCount();
    }

    @Override
    public Integer getCertificationProgrammeCount() {

        return certificationProgramme.getProgrammeCount();
    }

    @Override
    public Integer getDiplomaProgrammeCount() {

        return diplomaProgramme.getProgrammeCount();
    }


    @Override
    public void addDiscountCoupons(DiscountCoupons discountCoupon) {

        discountCoupons.add(discountCoupon);

    }


    @Override
    public Double getDegreeProgrammeCost() {

        return degreeProgramme.getProgrammeFee();
    }


    @Override
    public Double getCertificationProgrammeCost() {

        return certificationProgramme.getProgrammeFee();
    }


    @Override
    public Double getDiplomaProgrammeCost() {

        return diplomaProgramme.getProgrammeFee();
    }


    @Override
    public Boolean containsDiscountCoupon(DiscountCoupons discountCoupon) {

        return discountCoupons.contains(discountCoupon);
    }

    @Override
    public Double getCertificationProgrammeDiscountAmount() {

        return certificationProgramme.getProgrammeDiscountAmount();
    }

    @Override
    public Double getDegreeProgrammeDiscountAmount() {

        return degreeProgramme.getProgrammeDiscountAmount();
    }

    @Override
    public Double getDiplomaProgrammeDiscountAmount() {

        return diplomaProgramme.getProgrammeDiscountAmount();
    }


}
