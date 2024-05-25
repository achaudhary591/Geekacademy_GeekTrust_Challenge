package com.geektrust.backend.service;

import com.geektrust.backend.dao.BillPrintDao;
import com.geektrust.backend.models.Student;
import com.geektrust.backend.models.DiscountCoupons;
import com.geektrust.backend.repository.BillingRepository;
import com.geektrust.backend.repository.IBillingRepository;
import com.geektrust.backend.repository.IStudentRepository;
import com.geektrust.backend.helpers.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.DisplayName;

import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("BillingServiceTest for ProMembers")
@ExtendWith(MockitoExtension.class)
class BillingServiceImplTest2 {

    private IBillingService billingService;

    private IStudentRepository studentRepository;

    
    private IBillingRepository billingRepository;

    
    private ICartService cartService;

    
    private IDiscountService discountService;

    @BeforeEach
    public void setup() {
        Student student = new Student();
        student.addProMembershipPlan();
        studentRepository = Mockito.mock(IStudentRepository.class);
        billingRepository = new BillingRepository(student);
        cartService = new CartService(studentRepository, billingRepository);
        discountService = new DiscountService(studentRepository, billingRepository);
        billingService = new BillingService(studentRepository, billingRepository, cartService, discountService);

    }

    
    @DisplayName("Test for B4G1 with ProMembership")
    @Test
    void calculateProgrammeBillWithProMembership1() {
        Student student1 = new Student();
        student1.addProMembershipPlan();
        when(studentRepository.getStudent()).thenReturn(student1);
        when(studentRepository.getDegreeProgrammeCount()).thenReturn(2);
        when(studentRepository.getDegreeProgrammeCost()).thenReturn(Constants.DEGREE_FEES * (1 - Constants.DEGREE_DISCOUNT));
        when(studentRepository.getDegreeProgrammeDiscountAmount()).thenReturn(Constants.DEGREE_DISCOUNT * Constants.DEGREE_FEES);
        when(studentRepository.getCertificationProgrammeCount()).thenReturn(2);
        when(studentRepository.getCertificationProgrammeCost()).thenReturn(Constants.CERTIFICATION_FEES * (1 - Constants.CERTIFICATION_DISCOUNT));
        when(studentRepository.getCertificationProgrammeDiscountAmount()).thenReturn(Constants.CERTIFICATION_FEES * Constants.CERTIFICATION_DISCOUNT);
        when(studentRepository.getDiplomaProgrammeCount()).thenReturn(0);
        when(studentRepository.getDiplomaProgrammeCost()).thenReturn(Constants.DIPLOMA_FEES * (1 - Constants.DIPLOMA_DISCOUNT));
        when(studentRepository.getDiplomaProgrammeDiscountAmount()).thenReturn(Constants.DIPLOMA_FEES * Constants.DIPLOMA_DISCOUNT);

        Double totalProgrammeCost = 2 * studentRepository.getCertificationProgrammeCost() 
            + 2 * studentRepository.getDegreeProgrammeCost() + Constants.PRO_MEMBERSHIP_FEE;

        Double proMembershipDiscount = 2 * studentRepository.getCertificationProgrammeDiscountAmount()
        + 2 * studentRepository.getDegreeProgrammeDiscountAmount();

        BillPrintDao bill = billingService.calculateProgrammeBill();

        Double discountAmount = Constants.CERTIFICATION_FEES * (1 - Constants.CERTIFICATION_DISCOUNT);

        Double totalAmount = totalProgrammeCost - discountAmount;

        assertNotNull(bill);
        assertEquals(totalProgrammeCost, bill.getTotalProgrammeFee());
        assertEquals(proMembershipDiscount, bill.getProMembershipDiscount());
        assertEquals(DiscountCoupons.B4G1, bill.getCouponDiscountType());
        assertEquals(discountAmount, bill.getCouponDiscountAmount());
        assertEquals(totalAmount, bill.getTotalBillValue());
    }

    @DisplayName("Test for DEAL_G20 with ProMembership")
    @Test
    void calculateProgrammeBillWithProMembership2() {
        Student student1 = new Student();
        student1.addProMembershipPlan();
        when(studentRepository.getStudent()).thenReturn(student1);
        when(studentRepository.containsDiscountCoupon(DiscountCoupons.DEAL_G20) ).thenReturn(true);
        //when(studentRepository.containsDiscountCoupon(DiscountCoupons.DEAL_G5) ).thenReturn(true);
        when(studentRepository.getDegreeProgrammeCount()).thenReturn(2);
        when(studentRepository.getDegreeProgrammeCost()).thenReturn(Constants.DEGREE_FEES * (1 - Constants.DEGREE_DISCOUNT));
        when(studentRepository.getDegreeProgrammeDiscountAmount()).thenReturn(Constants.DEGREE_DISCOUNT * Constants.DEGREE_FEES);
        when(studentRepository.getCertificationProgrammeCount()).thenReturn(1);
        when(studentRepository.getCertificationProgrammeCost()).thenReturn(Constants.CERTIFICATION_FEES * (1 - Constants.CERTIFICATION_DISCOUNT));
        when(studentRepository.getCertificationProgrammeDiscountAmount()).thenReturn(Constants.CERTIFICATION_FEES * Constants.CERTIFICATION_DISCOUNT);
        when(studentRepository.getDiplomaProgrammeCount()).thenReturn(0);
        when(studentRepository.getDiplomaProgrammeCost()).thenReturn(Constants.DIPLOMA_FEES * (1 - Constants.DIPLOMA_DISCOUNT));
        when(studentRepository.getDiplomaProgrammeDiscountAmount()).thenReturn(Constants.DIPLOMA_FEES * Constants.DIPLOMA_DISCOUNT);


        Double totalProgrammeCost = 1 * studentRepository.getCertificationProgrammeCost()
            + 2 * studentRepository.getDegreeProgrammeCost() + Constants.PRO_MEMBERSHIP_FEE;

        Double proMembershipDiscount = 1 * studentRepository.getCertificationProgrammeDiscountAmount() 
        + 2 * studentRepository.getDegreeProgrammeDiscountAmount();

        Double discountAmount = totalProgrammeCost * Constants.DEAL_G20_DISCOUNT;

        Double totalAmount = totalProgrammeCost - discountAmount;

        BillPrintDao bill = billingService.calculateProgrammeBill();

        assertNotNull(bill);
        assertEquals(totalProgrammeCost, bill.getTotalProgrammeFee());
        assertEquals(proMembershipDiscount, bill.getProMembershipDiscount());
        assertEquals(DiscountCoupons.DEAL_G20, bill.getCouponDiscountType());
        assertEquals(discountAmount, bill.getCouponDiscountAmount());
        assertEquals(totalAmount, bill.getTotalBillValue());

    }

    @DisplayName("Test for DEAL_G5 with ProMembership provided DEAL_G20 Coupon is not explicity provided")
    @Test
    void calculateProgrammeBillWithProMembership3() {
        Student student1 = new Student();
        student1.addProMembershipPlan();
        when(studentRepository.getStudent()).thenReturn(student1);
        when(studentRepository.containsDiscountCoupon(DiscountCoupons.DEAL_G20) ).thenReturn(false);
        when(studentRepository.containsDiscountCoupon(DiscountCoupons.DEAL_G5) ).thenReturn(true);
        when(studentRepository.getDegreeProgrammeCount()).thenReturn(2);
        when(studentRepository.getDegreeProgrammeCost()).thenReturn(Constants.DEGREE_FEES * (1 - Constants.DEGREE_DISCOUNT));
        when(studentRepository.getDegreeProgrammeDiscountAmount()).thenReturn(Constants.DEGREE_FEES * Constants.DEGREE_DISCOUNT);
        when(studentRepository.getCertificationProgrammeCount()).thenReturn(1);
        when(studentRepository.getCertificationProgrammeCost()).thenReturn(Constants.CERTIFICATION_FEES * (1 - Constants.CERTIFICATION_DISCOUNT));
        when(studentRepository.getCertificationProgrammeDiscountAmount()).thenReturn(Constants.CERTIFICATION_FEES * Constants.CERTIFICATION_DISCOUNT);
        when(studentRepository.getDiplomaProgrammeCount()).thenReturn(0);
        when(studentRepository.getDiplomaProgrammeCost()).thenReturn(Constants.DIPLOMA_FEES * (1 - Constants.DIPLOMA_DISCOUNT));
        when(studentRepository.getDiplomaProgrammeDiscountAmount()).thenReturn(Constants.DIPLOMA_FEES * Constants.DIPLOMA_DISCOUNT);


        Double totalProgrammeCost = 1 * studentRepository.getCertificationProgrammeCost() 
            + 2 * studentRepository.getDegreeProgrammeCost() + Constants.PRO_MEMBERSHIP_FEE;

        Double proMembershipDiscount = 1 * studentRepository.getCertificationProgrammeDiscountAmount() 
        + 2 * studentRepository.getDegreeProgrammeDiscountAmount();

        Double discountAmount = totalProgrammeCost * Constants.DEAL_G5_DISCOUNT;

        BillPrintDao bill = billingService.calculateProgrammeBill();

        assertNotNull(bill);
        assertEquals(totalProgrammeCost, bill.getTotalProgrammeFee());
        assertEquals(proMembershipDiscount, bill.getProMembershipDiscount());
        assertEquals(DiscountCoupons.DEAL_G5, bill.getCouponDiscountType());
        assertEquals(discountAmount, bill.getCouponDiscountAmount());
    }

    @DisplayName("Test for DEAL_G5 with ProMembership provided DEAL_G20 Coupon is explicitly provided")
    @Test
    void calculateProgrammeBillWithProMembership4() {
        Student student1 = new Student();
        student1.addProMembershipPlan();
        when(studentRepository.getStudent()).thenReturn(student1);
        when(studentRepository.containsDiscountCoupon(DiscountCoupons.DEAL_G20) ).thenReturn(true);
        when(studentRepository.containsDiscountCoupon(DiscountCoupons.DEAL_G5) ).thenReturn(true);
        when(studentRepository.getDegreeProgrammeCount()).thenReturn(0);
        when(studentRepository.getDegreeProgrammeCost()).thenReturn(Constants.DEGREE_FEES * (1 - Constants.DEGREE_DISCOUNT));
        when(studentRepository.getDegreeProgrammeDiscountAmount()).thenReturn(Constants.DEGREE_FEES * Constants.DEGREE_DISCOUNT);
        when(studentRepository.getCertificationProgrammeCount()).thenReturn(1);
        when(studentRepository.getCertificationProgrammeCost()).thenReturn(Constants.CERTIFICATION_FEES * (1 - Constants.CERTIFICATION_DISCOUNT));
        when(studentRepository.getCertificationProgrammeDiscountAmount()).thenReturn(Constants.CERTIFICATION_FEES * Constants.CERTIFICATION_DISCOUNT);
        when(studentRepository.getDiplomaProgrammeCount()).thenReturn(1);
        when(studentRepository.getDiplomaProgrammeCost()).thenReturn(Constants.DIPLOMA_FEES * (1 - Constants.DIPLOMA_DISCOUNT));
        when(studentRepository.getDiplomaProgrammeDiscountAmount()).thenReturn(Constants.DIPLOMA_FEES * Constants.DIPLOMA_DISCOUNT);


        Double totalProgrammeCost = 1 * studentRepository.getCertificationProgrammeCost()
            + 1 * studentRepository.getDiplomaProgrammeCost() + Constants.PRO_MEMBERSHIP_FEE;

        Double proMembershipDiscount = 1 * studentRepository.getCertificationProgrammeDiscountAmount()
        + 1 * studentRepository.getDiplomaProgrammeDiscountAmount();

        Double enrollmentFee = Constants.ENROLLMENT_FEE;

        Double discountAmount = totalProgrammeCost * Constants.DEAL_G5_DISCOUNT;

        Double totalAmount = totalProgrammeCost - discountAmount + enrollmentFee;

        BillPrintDao bill = billingService.calculateProgrammeBill();

        assertNotNull(bill);
        assertEquals(totalProgrammeCost, bill.getTotalProgrammeFee());
        assertEquals(proMembershipDiscount, bill.getProMembershipDiscount());
        assertEquals(DiscountCoupons.DEAL_G5, bill.getCouponDiscountType());
        assertEquals(discountAmount, bill.getCouponDiscountAmount());
        assertEquals(totalAmount, bill.getTotalBillValue());
    }
}