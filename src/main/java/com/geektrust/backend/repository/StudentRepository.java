package com.geektrust.backend.repository;

import java.util.ArrayList;
import java.util.List;

import com.geektrust.backend.models.CertificationProgramme;
import com.geektrust.backend.models.DegreeProgramme;
import com.geektrust.backend.models.DiplomaProgramme;
import com.geektrust.backend.models.DiscountCoupons;
import com.geektrust.backend.models.Programme;
import com.geektrust.backend.enums.ProgrammeCategoryEnum;
import com.geektrust.backend.models.Student;


public class StudentRepository implements IStudentRepository{

    private Student student;
    private Programme certificationProgramme;
    private Programme degreeProgramme;
    private Programme diplomaProgramme;
    private List<DiscountCoupons> discountCoupons;


    public StudentRepository(Student student){
        this.student = student;
        certificationProgramme = new CertificationProgramme();
        degreeProgramme = new DegreeProgramme();
        diplomaProgramme = new DiplomaProgramme();
        discountCoupons = new ArrayList<>();
    }

    public StudentRepository(Student student, CertificationProgramme certificationProgramme, DegreeProgramme degreeProgramme, DiplomaProgramme diplomaProgramme){
        this(student);
        this.certificationProgramme = certificationProgramme;
        this.degreeProgramme = degreeProgramme;
        this.diplomaProgramme = diplomaProgramme;
        discountCoupons = new ArrayList<>();
    }



    // public List<DiscountCoupons> getDiscountCoupons() {
    //     return discountCoupons;
    // }

    
    public Student getStudent() {
        return student;
    }

    public Integer getTotalProgrammeCount() {
        return certificationProgramme.getProgrammeCount() + degreeProgramme.getProgrammeCount() + diplomaProgramme.getProgrammeCount();
    }

    @Override
    public void addProgramsToCart(ProgrammeCategoryEnum programmeCategoryEnum, Integer quantity) {
        if(programmeCategoryEnum == ProgrammeCategoryEnum.CERTIFICATION) {
            certificationProgramme.addProgramme(quantity);
        }
        else if(programmeCategoryEnum == ProgrammeCategoryEnum.DEGREE) {
            degreeProgramme.addProgramme(quantity);
        }
        else {
            diplomaProgramme.addProgramme(quantity);
        }
    }

    // @Override
    // public void addDiscountCoupons(DiscountCoupons discountCoupons) {
    //     this.discountCoupons.add(discountCoupons);
        
    // }

    @Override
    public void addProMembershipPlan() {
        student.addProMembershipPlan();
        certificationProgramme.addProMembershipDiscountCoupon();
        degreeProgramme.addProMembershipDiscountCoupon();
        diplomaProgramme.addProMembershipDiscountCoupon();
        
    }

    @Override
    public Integer getDegreeProgrammeCount() {
        // TODO Auto-generated method stub
        return degreeProgramme.getProgrammeCount();
    }

    @Override
    public Integer getCertificationProgrammeCount() {
        // TODO Auto-generated method stub
        return certificationProgramme.getProgrammeCount();
    }

    @Override
    public Integer getDiplomaProgrammeCount() {
        // TODO Auto-generated method stub
        return diplomaProgramme.getProgrammeCount();
    }



    @Override
    public void addDiscountCoupons(DiscountCoupons discountCoupon) {
        // TODO Auto-generated method stub
        discountCoupons.add(discountCoupon);
        
    }



    @Override
    public Double getDegreeProgrammeCost() {
        // TODO Auto-generated method stub
        return degreeProgramme.getProgrammeFee();
    }



    @Override
    public Double getCertificationProgrammeCost() {
        // TODO Auto-generated method stub
        return certificationProgramme.getProgrammeFee();
    }



    @Override
    public Double getDiplomaProgrammeCost() {
        // TODO Auto-generated method stub
        return diplomaProgramme.getProgrammeFee();
    }



    @Override
    public Double getDegreeProgrammeDiscount() {
        // TODO Auto-generated method stub
        return degreeProgramme.getProgrammeDiscount();
    }



    @Override
    public Double getCertificationProgrammeDiscount() {
        // TODO Auto-generated method stub
        return certificationProgramme.getProgrammeDiscount();
    }



    @Override
    public Double getDiplomaProgrammeDiscount() {
        // TODO Auto-generated method stub
        return diplomaProgramme.getProgrammeDiscount();
    }



    @Override
    public Boolean containsDiscountCoupon(DiscountCoupons discountCoupon) {
        // TODO Auto-generated method stub
        return discountCoupons.contains(discountCoupon);
    }

    @Override
    public Double getCertificationProgrammeDiscountAmount() {
        // TODO Auto-generated method stub
        return certificationProgramme.getProgrammeDiscountAmount();
    }

    @Override
    public Double getDegreeProgrammeDiscountAmount() {
        // TODO Auto-generated method stub
        return degreeProgramme.getProgrammeDiscountAmount();
    }

    @Override
    public Double getDiplomaProgrammeDiscountAmount() {
        // TODO Auto-generated method stub
        return diplomaProgramme.getProgrammeDiscountAmount();
    }



}
