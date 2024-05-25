package com.example.geektrust.repository;

import com.example.geektrust.enums.ProgrammeCategoryEnum;
import com.example.geektrust.models.*;

import java.util.ArrayList;
import java.util.List;


public class StudentRepository implements IStudentRepository {

    private final Student student;
    private IProgramme certificationIProgramme;
    private IProgramme degreeIProgramme;
    private IProgramme diplomaIProgramme;
    private List<DiscountCoupons> discountCoupons;


    public StudentRepository(Student student) {
        this.student = student;
        certificationIProgramme = new CertificationIProgramme();
        degreeIProgramme = new DegreeIProgramme();
        diplomaIProgramme = new DiplomaIProgramme();
        discountCoupons = new ArrayList<>();
    }

    public StudentRepository(Student student, CertificationIProgramme certificationProgramme, DegreeIProgramme degreeProgramme, DiplomaIProgramme diplomaProgramme) {
        this(student);
        this.certificationIProgramme = certificationProgramme;
        this.degreeIProgramme = degreeProgramme;
        this.diplomaIProgramme = diplomaProgramme;
        discountCoupons = new ArrayList<>();
    }


    public Student getStudent() {
        return student;
    }

    public Integer getTotalProgrammeCount() {
        return certificationIProgramme.getProgrammeCount() + degreeIProgramme.getProgrammeCount() + diplomaIProgramme.getProgrammeCount();
    }

    @Override
    public void addProgrammesToCart(ProgrammeCategoryEnum programmeCategoryEnum, Integer quantity) {
        if (programmeCategoryEnum == ProgrammeCategoryEnum.CERTIFICATION) {
            certificationIProgramme.addProgramme(quantity);
        } else if (programmeCategoryEnum == ProgrammeCategoryEnum.DEGREE) {
            degreeIProgramme.addProgramme(quantity);
        } else {
            diplomaIProgramme.addProgramme(quantity);
        }
    }

    @Override
    public void addProMembershipPlan() {
        student.addProMembershipPlan();
        certificationIProgramme.addProMembershipDiscountCoupon();
        degreeIProgramme.addProMembershipDiscountCoupon();
        diplomaIProgramme.addProMembershipDiscountCoupon();

    }

    @Override
    public Integer getDegreeProgrammeCount() {

        return degreeIProgramme.getProgrammeCount();
    }

    @Override
    public Integer getCertificationProgrammeCount() {

        return certificationIProgramme.getProgrammeCount();
    }

    @Override
    public Integer getDiplomaProgrammeCount() {

        return diplomaIProgramme.getProgrammeCount();
    }


    @Override
    public void addDiscountCoupons(DiscountCoupons discountCoupon) {

        discountCoupons.add(discountCoupon);

    }


    @Override
    public Double getDegreeProgrammeCost() {

        return degreeIProgramme.getProgrammeFee();
    }


    @Override
    public Double getCertificationProgrammeCost() {

        return certificationIProgramme.getProgrammeFee();
    }


    @Override
    public Double getDiplomaProgrammeCost() {

        return diplomaIProgramme.getProgrammeFee();
    }


    @Override
    public Boolean containsDiscountCoupon(DiscountCoupons discountCoupon) {

        return discountCoupons.contains(discountCoupon);
    }

    @Override
    public Double getCertificationProgrammeDiscountAmount() {

        return certificationIProgramme.getProgrammeDiscountAmount();
    }

    @Override
    public Double getDegreeProgrammeDiscountAmount() {

        return degreeIProgramme.getProgrammeDiscountAmount();
    }

    @Override
    public Double getDiplomaProgrammeDiscountAmount() {

        return diplomaIProgramme.getProgrammeDiscountAmount();
    }


}
