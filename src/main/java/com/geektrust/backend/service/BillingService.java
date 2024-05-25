package com.geektrust.backend.service;

import com.geektrust.backend.dao.BillPrintDao;
import com.geektrust.backend.helpers.Constants;
import com.geektrust.backend.models.DiscountCoupons;
import com.geektrust.backend.repository.IBillingRepository;
import com.geektrust.backend.repository.IStudentRepository;

public class BillingService implements IBillingService {


    private final IStudentRepository studentRepository;
    private final IBillingRepository billingRepository;

    private final ICartService cartService;
    private final IDiscountService discountService;

    public BillingService(IStudentRepository studentRepository, IBillingRepository billingRepository, ICartService cartService, IDiscountService discountService) {
        this.studentRepository = studentRepository;
        this.billingRepository = billingRepository;
        this.cartService = cartService;
        this.discountService = discountService;
    }


    private void calculateTotalCartItemCost() {
        cartService.calculateTotalProgrammeCost();
        if (studentRepository.getStudent().getProMembershipStatus()) {
            cartService.calculateTotalProMembershipDiscount();
        }
    }

    private void calculateDiscount() {
        int totalProgrammeCount = studentRepository.getCertificationProgrammeCount() + studentRepository.getDegreeProgrammeCount() + studentRepository.getDiplomaProgrammeCount();
        if (totalProgrammeCount >= 4) {
            discountService.applyB4G1Discount();
        } else if (studentRepository.containsDiscountCoupon(DiscountCoupons.DEAL_G20) && billingRepository.getTotalProgrammeFees() >= Constants.PROGRAMME_COST_FOR_G20_DISCOUNT) {
            discountService.applyDealG20Discount();
        } else if (studentRepository.containsDiscountCoupon(DiscountCoupons.DEAL_G5) && totalProgrammeCount >= Constants.PROGRAMME_COUNT_FOR_G5_DISCOUNT) {
            discountService.applyDealG5Discount();
        } else {
            discountService.applyNoDiscount();
        }

    }

    private void checkEnrollmentEligibility() {
        Double totalAmount = billingRepository.getTotalAmount();
        if (totalAmount < Constants.PROGRAMME_COST_THRESHOLD_FOR_ENROLLMENT_FEE) {
            billingRepository.addEnrollmentFee();
            billingRepository.setTotalAmount(billingRepository.getTotalAmount() + billingRepository.getEnrollmentFee());
        }
    }

    @Override
    public BillPrintDao calculateProgrammeBill() {
        calculateTotalCartItemCost();
        calculateDiscount();
        checkEnrollmentEligibility();
        return new BillPrintDao(
                billingRepository.getTotalProgrammeFees(),
                billingRepository.getCouponDiscount(),
                billingRepository.getDiscountAmount(),
                billingRepository.getProMembershipDiscount(),
                billingRepository.getProMembershipFee(),
                billingRepository.getEnrollmentFee(),
                billingRepository.getTotalAmount());

    }


}
