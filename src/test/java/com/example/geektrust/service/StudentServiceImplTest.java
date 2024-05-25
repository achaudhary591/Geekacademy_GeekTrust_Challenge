package com.example.geektrust.service;


import com.example.geektrust.enums.ProgrammeCategoryEnum;
import com.example.geektrust.exceptions.InvalidDiscountCouponException;
import com.example.geektrust.exceptions.InvalidProgrammeCategoryException;
import com.example.geektrust.models.DiscountCoupons;
import com.example.geektrust.repository.IStudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("StudentServiceTest")
@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {


    private IStudentService studentService;

    private IStudentRepository studentRepository;

    @BeforeEach
    public void setup() {
        studentRepository = Mockito.mock(IStudentRepository.class);
        studentService = new StudentService(studentRepository);
    }


    @Test
    void addProgrammes() {
        studentService.addProgrammes("DEGREE", 2);
        verify(studentRepository, times(1)).addProgrammesToCart(ProgrammeCategoryEnum.DEGREE, 2);
    }

    @Test
    void addInvalidProgrammes() {
        assertThrows(InvalidProgrammeCategoryException.class, () -> studentService.addProgrammes("INVALID_PROGRAM", 2));
    }

    @Test
    void setProMembershipPlan() {
        studentService.setProMembershipPlan();
        verify(studentRepository, times(1)).addProMembershipPlan();
    }

    @Test
    void addValidDiscountCoupon() {
        studentService.addDiscountCoupon("DEAL_G20");
        verify(studentRepository, times(1)).addDiscountCoupons(DiscountCoupons.DEAL_G20);
    }

    @Test
    void addInvalidDiscountCoupon() {
        assertThrows(InvalidDiscountCouponException.class, () -> studentService.addDiscountCoupon("INVALID_COUPON"));
    }
}
