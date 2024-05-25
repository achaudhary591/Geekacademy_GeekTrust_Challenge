package com.geektrust.backend.commands;

import com.geektrust.backend.dao.BillPrintDao;
import com.geektrust.backend.models.DiscountCoupons;
import com.geektrust.backend.service.IBillingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collections;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PrintBillCommandTest {

    
    private ICommand printBillCommand;

    
    private IBillingService billingService;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        billingService = Mockito.mock(IBillingService.class);
        printBillCommand = new PrintBillCommand(billingService);
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void execute() {
        BillPrintDao billPrintDao = new BillPrintDao(1000, DiscountCoupons.NONE, 0, 100, 100, 500, 1600);
        when(billingService.calculateBill()).thenReturn(billPrintDao);

        printBillCommand.execute(Collections.emptyList());

        String expectedOutput = "SUB_TOTAL 1000.00\n" +
                "COUPON_DISCOUNT NONE 0.00\n" +
                "TOTAL_PRO_DISCOUNT 100.00\n" +
                "PRO_MEMBERSHIP_FEE 100.00\n" +
                "ENROLLMENT_FEE 500.00\n" +
                "TOTAL 1600.00\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void execute1() {
        BillPrintDao billPrintDao = new BillPrintDao(1000, DiscountCoupons.DEAL_G20, 20, 100, 100, 500, 1580);
        when(billingService.calculateBill()).thenReturn(billPrintDao);
        printBillCommand.execute(Collections.emptyList());

        String expectedOutput = "SUB_TOTAL 1000.00\n" +
                "COUPON_DISCOUNT DEAL_G20 20.00\n" +
                "TOTAL_PRO_DISCOUNT 100.00\n" +
                "PRO_MEMBERSHIP_FEE 100.00\n" +
                "ENROLLMENT_FEE 500.00\n" +
                "TOTAL 1580.00\n";
        assertEquals(expectedOutput, outContent.toString());
    }
}
