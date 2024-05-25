package com.geektrust.backend.service;

import com.geektrust.backend.helpers.Constants;
import com.geektrust.backend.repository.IBillingRepository;
import com.geektrust.backend.repository.IStudentRepository;

public class CartService implements ICartService {

    private final IStudentRepository studentRepository;
    private final IBillingRepository billingRepository;

    public CartService(IStudentRepository studentRepository,
                       IBillingRepository billingRepository) {
        this.billingRepository = billingRepository;
        this.studentRepository = studentRepository;

    }

    public void calculateTotalProgrammeCost() {
        Double totalCost = Constants.DOUBLE_INITIALIZE_VALUE;
        totalCost += studentRepository.getCertificationProgrammeCount() * studentRepository.getCertificationProgrammeCost();
        totalCost += studentRepository.getDegreeProgrammeCount() * studentRepository.getDegreeProgrammeCost();
        totalCost += studentRepository.getDiplomaProgrammeCount() * studentRepository.getDiplomaProgrammeCost();
        totalCost += billingRepository.getProMembershipFee();
        billingRepository.setTotalProgrammeFees(totalCost);
        billingRepository.setTotalAmount(totalCost);
    }

    public void calculateTotalProMembershipDiscount() {
        Double proMembershipDiscount = Constants.DOUBLE_INITIALIZE_VALUE;
        proMembershipDiscount += studentRepository.getCertificationProgrammeCount() * studentRepository.getCertificationProgrammeDiscountAmount();
        proMembershipDiscount += studentRepository.getDegreeProgrammeCount() * studentRepository.getDegreeProgrammeDiscountAmount();
        proMembershipDiscount += studentRepository.getDiplomaProgrammeCount() * studentRepository.getDiplomaProgrammeDiscountAmount();
        billingRepository.setProMembershipDiscount(proMembershipDiscount);
    }

}