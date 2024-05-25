package com.geektrust.backend;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collections;


class AppTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor,true));
    }

    @Test
    @DisplayName("Integration Test #1")
    void runTest1(){
        //Arrange
        String argument= "sample_input/input1.txt";
        String expectedOutput = "SUB_TOTAL 13000.00\n" +
                "COUPON_DISCOUNT B4G1 2500.00\n" +
                "TOTAL_PRO_DISCOUNT 0.00\n" +
                "PRO_MEMBERSHIP_FEE 0.00\n" +
                "ENROLLMENT_FEE 0.00\n" +
                "TOTAL 10500.00";
        //Act
        App.run(Collections.singletonList(argument));
        //Assert
        Assertions.assertEquals(expectedOutput,outputStreamCaptor.toString().trim());
    }

    @Test
    @DisplayName("Integration Test #2")
    void runTest2(){
        //Arrange
        String argument= "sample_input/input2.txt";
        String expectedOutput = "SUB_TOTAL 10000.00\n" +
                "COUPON_DISCOUNT DEAL_G20 2000.00\n" +
                "TOTAL_PRO_DISCOUNT 0.00\n" +
                "PRO_MEMBERSHIP_FEE 0.00\n" +
                "ENROLLMENT_FEE 0.00\n" +
                "TOTAL 8000.00";
        //Act
        App.run(Collections.singletonList(argument));
        //Assert
        Assertions.assertEquals(expectedOutput,outputStreamCaptor.toString().trim());
    }

    @Test
    @DisplayName("Integration Test #3")
    void runTest3(){
        //Arrange
        String argument= "sample_input/input3.txt";
        String expectedOutput = "SUB_TOTAL 5615.00\n" +
                "COUPON_DISCOUNT NONE 0.00\n" +
                "TOTAL_PRO_DISCOUNT 85.00\n" +
                "PRO_MEMBERSHIP_FEE 200.00\n" +
                "ENROLLMENT_FEE 500.00\n" +
                "TOTAL 6115.00";
        //Act
        App.run(Collections.singletonList(argument));
        //Assert
        Assertions.assertEquals(expectedOutput,outputStreamCaptor.toString().trim());
    }
}