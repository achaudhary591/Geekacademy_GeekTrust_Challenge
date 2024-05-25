package com.example.geektrust.appConfig;

import com.example.geektrust.commands.*;
import com.example.geektrust.models.Student;
import com.example.geektrust.repository.BillingRepository;
import com.example.geektrust.repository.IBillingRepository;
import com.example.geektrust.repository.IStudentRepository;
import com.example.geektrust.repository.StudentRepository;
import com.example.geektrust.service.*;
import com.example.geektrust.commands.*;
import com.example.geektrust.commands.*;
import com.example.geektrust.service.*;
import com.example.geektrust.service.*;


public class ApplicationConfig {

    private final CommandInvoker commandInvoker = new CommandInvoker();
    Student student = new Student();
    private final IStudentRepository studentRepository = new StudentRepository(student);
    private final IStudentService studentService = new StudentService(studentRepository);
    private final AddProgrammeCommand addProgrammeCommand = new AddProgrammeCommand(studentService);
    private final ApplyCouponCommand applyCouponCommand = new ApplyCouponCommand(studentService);
    private final AddProMembershipCommand addProMembershipCommand = new AddProMembershipCommand(studentService);
    private final IBillingRepository billingRepository = new BillingRepository(student);
    private final ICartService cartService = new CartService(studentRepository, billingRepository);
    private final IDiscountService discountService = new DiscountService(studentRepository, billingRepository);
    private final IBillingService billingService = new BillingService(studentRepository, billingRepository, cartService, discountService);
    private final PrintBillCommand printBillCommand = new PrintBillCommand(billingService);

    public CommandInvoker getCommandInvoker() {
        commandInvoker.register("ADD_PROGRAMME", addProgrammeCommand);
        commandInvoker.register("APPLY_COUPON", applyCouponCommand);
        commandInvoker.register("ADD_PRO_MEMBERSHIP", addProMembershipCommand);
        commandInvoker.register("PRINT_BILL", printBillCommand);

        return commandInvoker;
    }


}
