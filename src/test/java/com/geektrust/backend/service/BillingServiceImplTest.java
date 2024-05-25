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

@DisplayName("BillingServiceTest for Non Pro Members")
@ExtendWith(MockitoExtension.class)
class BillingServiceImplTest {

    private IBillingService billingService;

    private IStudentRepository studentRepository;

    
    private IBillingRepository billingRepository;

    
    private ICartService cartService;

    
    private IDiscountService discountService;

    @BeforeEach
    public void setup() {
        Student student = new Student();
        studentRepository = Mockito.mock(IStudentRepository.class);
        billingRepository = new BillingRepository(student);
        cartService = new CartService(studentRepository, billingRepository);
        discountService = new DiscountService(studentRepository, billingRepository);
        billingService = new BillingService(studentRepository, billingRepository, cartService, discountService);

    }

    @DisplayName("Test for B4G1 without Pro Membership")
    @Test
    void calculateProgrammeBillWithoutProMembership1() {
        Student student1 = new Student();
        when(studentRepository.getStudent()).thenReturn(student1);
       // when(studentRepository.containsDiscountCoupon(DiscountCoupons.DEAL_G20) ).thenReturn(true);
        when(studentRepository.getDegreeProgrammeCount()).thenReturn(2);
        when(studentRepository.getDegreeProgrammeCost()).thenReturn(Constants.DEGREE_FEES);
        when(studentRepository.getCertificationProgrammeCount()).thenReturn(2);
        when(studentRepository.getCertificationProgrammeCost()).thenReturn(Constants.CERTIFICATION_FEES);
        when(studentRepository.getDiplomaProgrammeCount()).thenReturn(1);
        when(studentRepository.getDiplomaProgrammeCost()).thenReturn(Constants.DIPLOMA_FEES);

        BillPrintDao bill = billingService.calculateProgrammeBill();

        Double totalProgrammeCost = 2 * studentRepository.getCertificationProgrammeCost()
            + 2 * studentRepository.getDegreeProgrammeCost()
            + 1 * studentRepository.getDiplomaProgrammeCost();

        Double discountAmount = Constants.DIPLOMA_FEES;

        Double totalAmount = totalProgrammeCost - discountAmount;

        
        assertNotNull(bill);
        assertEquals(totalProgrammeCost, bill.getTotalProgrammeFee());
        assertEquals(0.0, bill.getProMembershipDiscount());
        assertEquals(DiscountCoupons.B4G1, bill.getCouponDiscountType());
        assertEquals(discountAmount, bill.getCouponDiscountAmount());
        assertEquals(totalAmount, bill.getTotalBillValue());
    }
    
    @DisplayName("Test for DEAL_G20 without Pro Membership")
    @Test
    void calculateProgrammeBillWithoutProMembership2() {
        Student student1 = new Student();
        when(studentRepository.getStudent()).thenReturn(student1);
        when(studentRepository.containsDiscountCoupon(DiscountCoupons.DEAL_G20) ).thenReturn(true);
        when(studentRepository.getDegreeProgrammeCount()).thenReturn(2);
        when(studentRepository.getDegreeProgrammeCost()).thenReturn(Constants.DEGREE_FEES);
        when(studentRepository.getCertificationProgrammeCount()).thenReturn(1);
        when(studentRepository.getCertificationProgrammeCost()).thenReturn(Constants.CERTIFICATION_FEES);
        when(studentRepository.getDiplomaProgrammeCount()).thenReturn(0);
        when(studentRepository.getDiplomaProgrammeCost()).thenReturn(Constants.DIPLOMA_FEES);

        BillPrintDao bill = billingService.calculateProgrammeBill();

        Double totalProgrammeCost = 1 * studentRepository.getCertificationProgrammeCost()
            + 2 * studentRepository.getDegreeProgrammeCost()
            + 0 * studentRepository.getDiplomaProgrammeCost();

        Double discountAmount = totalProgrammeCost * Constants.DEAL_G20_DISCOUNT;

        Double totalAmount = totalProgrammeCost - discountAmount;

        
        assertNotNull(bill);
        assertEquals(totalProgrammeCost, bill.getTotalProgrammeFee());
        assertEquals(0.0, bill.getProMembershipDiscount());
        assertEquals(DiscountCoupons.DEAL_G20, bill.getCouponDiscountType());
        assertEquals(discountAmount, bill.getCouponDiscountAmount());
        assertEquals(totalAmount, bill.getTotalBillValue());
    }

    @DisplayName("Test for No Discount if no discount coupons are provided without Pro Membership")
    @Test
    void calculateProgrammeBillWithoutProMembership3() {
        Student student1 = new Student();
        when(studentRepository.getStudent()).thenReturn(student1);
        when(studentRepository.containsDiscountCoupon(DiscountCoupons.DEAL_G20) ).thenReturn(false);
        when(studentRepository.containsDiscountCoupon(DiscountCoupons.DEAL_G5) ).thenReturn(false);
        when(studentRepository.getDegreeProgrammeCount()).thenReturn(2);
        when(studentRepository.getDegreeProgrammeCost()).thenReturn(Constants.DEGREE_FEES);
        when(studentRepository.getCertificationProgrammeCount()).thenReturn(1);
        when(studentRepository.getCertificationProgrammeCost()).thenReturn(Constants.CERTIFICATION_FEES);
        when(studentRepository.getDiplomaProgrammeCount()).thenReturn(0);
        when(studentRepository.getDiplomaProgrammeCost()).thenReturn(Constants.DIPLOMA_FEES);

        BillPrintDao bill = billingService.calculateProgrammeBill();

        Double totalProgrammeCost = 1 * studentRepository.getCertificationProgrammeCost()
            + 2 * studentRepository.getDegreeProgrammeCost()
            + 0 * studentRepository.getDiplomaProgrammeCost();

        Double discountAmount = 0.0;

        Double totalAmount = totalProgrammeCost - discountAmount;

        
        assertNotNull(bill);
        assertEquals(totalProgrammeCost, bill.getTotalProgrammeFee());
        assertEquals(0.0, bill.getProMembershipDiscount());
        assertEquals(DiscountCoupons.NONE, bill.getCouponDiscountType());
        assertEquals(discountAmount, bill.getCouponDiscountAmount());
        assertEquals(totalAmount, bill.getTotalBillValue());
    }

    @DisplayName("Test for No Discount even if discount coupons are provided without Pro Membership")
    @Test
    void calculateProgrammeBillWithoutProMembership4() {
        Student student1 = new Student();
        when(studentRepository.getStudent()).thenReturn(student1);
        when(studentRepository.containsDiscountCoupon(DiscountCoupons.DEAL_G20) ).thenReturn(true);
        when(studentRepository.containsDiscountCoupon(DiscountCoupons.DEAL_G5) ).thenReturn(true);
        when(studentRepository.getDegreeProgrammeCount()).thenReturn(0);
        when(studentRepository.getDegreeProgrammeCost()).thenReturn(Constants.DEGREE_FEES);
        when(studentRepository.getCertificationProgrammeCount()).thenReturn(1);
        when(studentRepository.getCertificationProgrammeCost()).thenReturn(Constants.CERTIFICATION_FEES);
        when(studentRepository.getDiplomaProgrammeCount()).thenReturn(0);
        when(studentRepository.getDiplomaProgrammeCost()).thenReturn(Constants.DIPLOMA_FEES);

        BillPrintDao bill = billingService.calculateProgrammeBill();

        Double totalProgrammeCost = 1 * studentRepository.getCertificationProgrammeCost()
            + 0 * studentRepository.getDegreeProgrammeCost()
            + 0 * studentRepository.getDiplomaProgrammeCost();

        Double discountAmount = 0.0;

        Double enrollmentFee = Constants.ENROLLMENT_FEE;

        Double totalAmount = totalProgrammeCost - discountAmount + enrollmentFee;

        
        assertNotNull(bill);
        assertEquals(totalProgrammeCost, bill.getTotalProgrammeFee());
        assertEquals(0.0, bill.getProMembershipDiscount());
        assertEquals(DiscountCoupons.NONE, bill.getCouponDiscountType());
        assertEquals(discountAmount, bill.getCouponDiscountAmount());
        assertEquals(totalAmount, bill.getTotalBillValue());
    }

    @DisplayName("Test for Enrollment Fee")
    @Test
    void calculateProgrammeBillWithEnrollmentFee() {
        Student student1 = new Student();
        when(studentRepository.getStudent()).thenReturn(student1);
        when(studentRepository.containsDiscountCoupon(DiscountCoupons.DEAL_G20) ).thenReturn(true);
        when(studentRepository.containsDiscountCoupon(DiscountCoupons.DEAL_G5) ).thenReturn(true);
        when(studentRepository.getDegreeProgrammeCount()).thenReturn(0);
        when(studentRepository.getDegreeProgrammeCost()).thenReturn(Constants.DEGREE_FEES);
        when(studentRepository.getCertificationProgrammeCount()).thenReturn(1);
        when(studentRepository.getCertificationProgrammeCost()).thenReturn(Constants.CERTIFICATION_FEES);
        when(studentRepository.getDiplomaProgrammeCount()).thenReturn(1);
        when(studentRepository.getDiplomaProgrammeCost()).thenReturn(Constants.DIPLOMA_FEES);

        BillPrintDao bill = billingService.calculateProgrammeBill();

        Double totalProgrammeCost = 1 * studentRepository.getCertificationProgrammeCost()
            + 0 * studentRepository.getDegreeProgrammeCost()
            + 1 * studentRepository.getDiplomaProgrammeCost();

        Double discountAmount = totalProgrammeCost * Constants.DEAL_G5_DISCOUNT;

        Double enrollmentFee = Constants.ENROLLMENT_FEE;

        Double totalAmount = totalProgrammeCost - discountAmount + enrollmentFee;

        
        assertNotNull(bill);
        assertEquals(totalProgrammeCost, bill.getTotalProgrammeFee());
        assertEquals(0.0, bill.getProMembershipDiscount());
        assertEquals(DiscountCoupons.DEAL_G5, bill.getCouponDiscountType());
        assertEquals(discountAmount, bill.getCouponDiscountAmount());
        assertEquals(enrollmentFee, bill.getEnrollmentFee());
        assertEquals(totalAmount, bill.getTotalBillValue());
    }
}






