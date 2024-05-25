package com.geektrust.backend.service;

import com.geektrust.backend.dao.BillPrintDao;
import com.geektrust.backend.models.Student;
import com.geektrust.backend.models.DiscountCoupons;
import com.geektrust.backend.repository.BillingRepository;
import com.geektrust.backend.repository.IBillingRepository;
import com.geektrust.backend.repository.IStudentRepository;
import com.geektrust.backend.utils.GeekdemyConstants;
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
    void calculateBillWithProMembership1() {
        Student student1 = new Student();
        student1.addProMembershipPlan();
        when(studentRepository.getStudent()).thenReturn(student1);
        when(studentRepository.getDegreeProgrammeCount()).thenReturn(2);
        when(studentRepository.getDegreeProgrammeCost()).thenReturn(GeekdemyConstants.DEGREE_FEES * (1 - GeekdemyConstants.DEGREE_DISCOUNT));
        when(studentRepository.getDegreeProgrammeDiscountAmount()).thenReturn(GeekdemyConstants.DEGREE_DISCOUNT * GeekdemyConstants.DEGREE_FEES);
        when(studentRepository.getCertificationProgrammeCount()).thenReturn(2);
        when(studentRepository.getCertificationProgrammeCost()).thenReturn(GeekdemyConstants.CERTIFICATION_FEES * (1 - GeekdemyConstants.CERTIFICATION_DISCOUNT));
        when(studentRepository.getCertificationProgrammeDiscountAmount()).thenReturn(GeekdemyConstants.CERTIFICATION_FEES * GeekdemyConstants.CERTIFICATION_DISCOUNT);
        when(studentRepository.getDiplomaProgrammeCount()).thenReturn(0);
        when(studentRepository.getDiplomaProgrammeCost()).thenReturn(GeekdemyConstants.DIPLOMA_FEES * (1 - GeekdemyConstants.DIPLOMA_DISCOUNT));
        when(studentRepository.getDiplomaProgrammeDiscountAmount()).thenReturn(GeekdemyConstants.DIPLOMA_FEES * GeekdemyConstants.DIPLOMA_DISCOUNT);

        Double totalProgrammeCost = 2 * studentRepository.getCertificationProgrammeCost() 
            + 2 * studentRepository.getDegreeProgrammeCost() + GeekdemyConstants.PRO_MEMBERSHIP_FEE;

        Double proMembershipDiscount = 2 * studentRepository.getCertificationProgrammeDiscountAmount()
        + 2 * studentRepository.getDegreeProgrammeDiscountAmount();

        BillPrintDao bill = billingService.calculateBill();

        Double discountAmount = GeekdemyConstants.CERTIFICATION_FEES * (1 - GeekdemyConstants.CERTIFICATION_DISCOUNT);

        Double totalAmount = totalProgrammeCost - discountAmount;

        assertNotNull(bill);
        assertEquals(totalProgrammeCost, bill.getTotalProgrammeFee());
        assertEquals(proMembershipDiscount, bill.getProMembershipDiscount());
        assertEquals(DiscountCoupons.B4G1, bill.getCouponDiscountType());
        assertEquals(discountAmount, bill.getCouponDiscountAmount());
        assertEquals(totalAmount, bill.getTotal());
    }

    @DisplayName("Test for DEAL_G20 with ProMembership")
    @Test
    void calculateBillWithProMembership2() {
        Student student1 = new Student();
        student1.addProMembershipPlan();
        when(studentRepository.getStudent()).thenReturn(student1);
        when(studentRepository.containsDiscountCoupon(DiscountCoupons.DEAL_G20) ).thenReturn(true);
        //when(studentRepository.containsDiscountCoupon(DiscountCoupons.DEAL_G5) ).thenReturn(true);
        when(studentRepository.getDegreeProgrammeCount()).thenReturn(2);
        when(studentRepository.getDegreeProgrammeCost()).thenReturn(GeekdemyConstants.DEGREE_FEES * (1 - GeekdemyConstants.DEGREE_DISCOUNT));
        when(studentRepository.getDegreeProgrammeDiscountAmount()).thenReturn(GeekdemyConstants.DEGREE_DISCOUNT * GeekdemyConstants.DEGREE_FEES);
        when(studentRepository.getCertificationProgrammeCount()).thenReturn(1);
        when(studentRepository.getCertificationProgrammeCost()).thenReturn(GeekdemyConstants.CERTIFICATION_FEES * (1 - GeekdemyConstants.CERTIFICATION_DISCOUNT));
        when(studentRepository.getCertificationProgrammeDiscountAmount()).thenReturn(GeekdemyConstants.CERTIFICATION_FEES * GeekdemyConstants.CERTIFICATION_DISCOUNT);
        when(studentRepository.getDiplomaProgrammeCount()).thenReturn(0);
        when(studentRepository.getDiplomaProgrammeCost()).thenReturn(GeekdemyConstants.DIPLOMA_FEES * (1 - GeekdemyConstants.DIPLOMA_DISCOUNT));
        when(studentRepository.getDiplomaProgrammeDiscountAmount()).thenReturn(GeekdemyConstants.DIPLOMA_FEES * GeekdemyConstants.DIPLOMA_DISCOUNT);


        Double totalProgrammeCost = 1 * studentRepository.getCertificationProgrammeCost()
            + 2 * studentRepository.getDegreeProgrammeCost() + GeekdemyConstants.PRO_MEMBERSHIP_FEE;

        Double proMembershipDiscount = 1 * studentRepository.getCertificationProgrammeDiscountAmount() 
        + 2 * studentRepository.getDegreeProgrammeDiscountAmount();

        Double discountAmount = totalProgrammeCost * GeekdemyConstants.DEAL_G20_DISCOUNT;

        Double totalAmount = totalProgrammeCost - discountAmount;

        BillPrintDao bill = billingService.calculateBill();

        assertNotNull(bill);
        assertEquals(totalProgrammeCost, bill.getTotalProgrammeFee());
        assertEquals(proMembershipDiscount, bill.getProMembershipDiscount());
        assertEquals(DiscountCoupons.DEAL_G20, bill.getCouponDiscountType());
        assertEquals(discountAmount, bill.getCouponDiscountAmount());
        assertEquals(totalAmount, bill.getTotal());

    }

    @DisplayName("Test for DEAL_G5 with ProMembership provided DEAL_G20 Coupon is not explicity provided")
    @Test
    void calculateBillWithProMembership3() {
        Student student1 = new Student();
        student1.addProMembershipPlan();
        when(studentRepository.getStudent()).thenReturn(student1);
        when(studentRepository.containsDiscountCoupon(DiscountCoupons.DEAL_G20) ).thenReturn(false);
        when(studentRepository.containsDiscountCoupon(DiscountCoupons.DEAL_G5) ).thenReturn(true);
        when(studentRepository.getDegreeProgrammeCount()).thenReturn(2);
        when(studentRepository.getDegreeProgrammeCost()).thenReturn(GeekdemyConstants.DEGREE_FEES * (1 - GeekdemyConstants.DEGREE_DISCOUNT));
        when(studentRepository.getDegreeProgrammeDiscountAmount()).thenReturn(GeekdemyConstants.DEGREE_FEES * GeekdemyConstants.DEGREE_DISCOUNT);
        when(studentRepository.getCertificationProgrammeCount()).thenReturn(1);
        when(studentRepository.getCertificationProgrammeCost()).thenReturn(GeekdemyConstants.CERTIFICATION_FEES * (1 - GeekdemyConstants.CERTIFICATION_DISCOUNT));
        when(studentRepository.getCertificationProgrammeDiscountAmount()).thenReturn(GeekdemyConstants.CERTIFICATION_FEES * GeekdemyConstants.CERTIFICATION_DISCOUNT);
        when(studentRepository.getDiplomaProgrammeCount()).thenReturn(0);
        when(studentRepository.getDiplomaProgrammeCost()).thenReturn(GeekdemyConstants.DIPLOMA_FEES * (1 - GeekdemyConstants.DIPLOMA_DISCOUNT));
        when(studentRepository.getDiplomaProgrammeDiscountAmount()).thenReturn(GeekdemyConstants.DIPLOMA_FEES * GeekdemyConstants.DIPLOMA_DISCOUNT);


        Double totalProgrammeCost = 1 * studentRepository.getCertificationProgrammeCost() 
            + 2 * studentRepository.getDegreeProgrammeCost() + GeekdemyConstants.PRO_MEMBERSHIP_FEE;

        Double proMembershipDiscount = 1 * studentRepository.getCertificationProgrammeDiscountAmount() 
        + 2 * studentRepository.getDegreeProgrammeDiscountAmount();

        Double discountAmount = totalProgrammeCost * GeekdemyConstants.DEAL_G5_DISCOUNT;

        BillPrintDao bill = billingService.calculateBill();

        assertNotNull(bill);
        assertEquals(totalProgrammeCost, bill.getTotalProgrammeFee());
        assertEquals(proMembershipDiscount, bill.getProMembershipDiscount());
        assertEquals(DiscountCoupons.DEAL_G5, bill.getCouponDiscountType());
        assertEquals(discountAmount, bill.getCouponDiscountAmount());
    }

    @DisplayName("Test for DEAL_G5 with ProMembership provided DEAL_G20 Coupon is explicitly provided")
    @Test
    void calculateBillWithProMembership4() {
        Student student1 = new Student();
        student1.addProMembershipPlan();
        when(studentRepository.getStudent()).thenReturn(student1);
        when(studentRepository.containsDiscountCoupon(DiscountCoupons.DEAL_G20) ).thenReturn(true);
        when(studentRepository.containsDiscountCoupon(DiscountCoupons.DEAL_G5) ).thenReturn(true);
        when(studentRepository.getDegreeProgrammeCount()).thenReturn(0);
        when(studentRepository.getDegreeProgrammeCost()).thenReturn(GeekdemyConstants.DEGREE_FEES * (1 - GeekdemyConstants.DEGREE_DISCOUNT));
        when(studentRepository.getDegreeProgrammeDiscountAmount()).thenReturn(GeekdemyConstants.DEGREE_FEES * GeekdemyConstants.DEGREE_DISCOUNT);
        when(studentRepository.getCertificationProgrammeCount()).thenReturn(1);
        when(studentRepository.getCertificationProgrammeCost()).thenReturn(GeekdemyConstants.CERTIFICATION_FEES * (1 - GeekdemyConstants.CERTIFICATION_DISCOUNT));
        when(studentRepository.getCertificationProgrammeDiscountAmount()).thenReturn(GeekdemyConstants.CERTIFICATION_FEES * GeekdemyConstants.CERTIFICATION_DISCOUNT);
        when(studentRepository.getDiplomaProgrammeCount()).thenReturn(1);
        when(studentRepository.getDiplomaProgrammeCost()).thenReturn(GeekdemyConstants.DIPLOMA_FEES * (1 - GeekdemyConstants.DIPLOMA_DISCOUNT));
        when(studentRepository.getDiplomaProgrammeDiscountAmount()).thenReturn(GeekdemyConstants.DIPLOMA_FEES * GeekdemyConstants.DIPLOMA_DISCOUNT);


        Double totalProgrammeCost = 1 * studentRepository.getCertificationProgrammeCost()
            + 1 * studentRepository.getDiplomaProgrammeCost() + GeekdemyConstants.PRO_MEMBERSHIP_FEE;

        Double proMembershipDiscount = 1 * studentRepository.getCertificationProgrammeDiscountAmount()
        + 1 * studentRepository.getDiplomaProgrammeDiscountAmount();

        Double enrollmentFee = GeekdemyConstants.ENROLLMENT_FEE;

        Double discountAmount = totalProgrammeCost * GeekdemyConstants.DEAL_G5_DISCOUNT;

        Double totalAmount = totalProgrammeCost - discountAmount + enrollmentFee;

        BillPrintDao bill = billingService.calculateBill();

        assertNotNull(bill);
        assertEquals(totalProgrammeCost, bill.getTotalProgrammeFee());
        assertEquals(proMembershipDiscount, bill.getProMembershipDiscount());
        assertEquals(DiscountCoupons.DEAL_G5, bill.getCouponDiscountType());
        assertEquals(discountAmount, bill.getCouponDiscountAmount());
        assertEquals(totalAmount, bill.getTotal());
    }
}