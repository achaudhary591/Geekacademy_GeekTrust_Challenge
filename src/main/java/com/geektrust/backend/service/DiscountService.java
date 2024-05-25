package com.geektrust.backend.service;


import com.geektrust.backend.models.DiscountCoupons;
import com.geektrust.backend.repository.IBillingRepository;
import com.geektrust.backend.repository.IStudentRepository;
import com.geektrust.backend.utils.GeekdemyConstants;

public class DiscountService implements IDiscountService{

    private IStudentRepository studentRepository;
    private IBillingRepository billingRepository;

    public DiscountService(IStudentRepository studentRepository,
            IBillingRepository billingRepository) {
        this.studentRepository = studentRepository;
        this.billingRepository = billingRepository;
    }

    public void applyB4G1Discount() {
        billingRepository.setCouponDiscount(DiscountCoupons.B4G1);
        Double discountAmount = 0.0;
        if(studentRepository.getDiplomaProgrammeCount() > 0) {
            discountAmount = studentRepository.getDiplomaProgrammeCost();
        } else if(studentRepository.getCertificationProgrammeCount() > 0) {
            discountAmount = studentRepository.getCertificationProgrammeCost();
        } else {
            discountAmount = studentRepository.getDegreeProgrammeCost();
        }
        billingRepository.setDiscountAmount(discountAmount);
        billingRepository.setTotalAmount(billingRepository.getTotalAmount() - discountAmount);
    }

    public void applyDealG20Discount() {
        billingRepository.setCouponDiscount(DiscountCoupons.DEAL_G20);
        billingRepository.setDiscountAmount(billingRepository.getTotalProgramFees() * GeekdemyConstants.DEAL_G20_DISCOUNT);
        billingRepository.setTotalAmount(billingRepository.getTotalAmount() - billingRepository.getDiscountAmount());
    }

    public void applyDealG5Discount() {
        billingRepository.setCouponDiscount(DiscountCoupons.DEAL_G5);
        billingRepository.setDiscountAmount(billingRepository.getTotalProgramFees() * GeekdemyConstants.DEAL_G5_DISCOUNT);
        billingRepository.setTotalAmount(billingRepository.getTotalAmount() - billingRepository.getDiscountAmount());
    }

    public void applyNoDiscount() {
        billingRepository.setCouponDiscount(DiscountCoupons.NONE);
        billingRepository.setDiscountAmount(0.0);
    }

    
}
