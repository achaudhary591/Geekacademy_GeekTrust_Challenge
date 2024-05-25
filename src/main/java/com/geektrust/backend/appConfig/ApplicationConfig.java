package com.geektrust.backend.appConfig;

import com.geektrust.backend.commands.*;
import com.geektrust.backend.models.Student;
import com.geektrust.backend.repository.BillingRepository;
import com.geektrust.backend.repository.IBillingRepository;
import com.geektrust.backend.repository.IStudentRepository;
import com.geektrust.backend.repository.StudentRepository;
import com.geektrust.backend.service.*;


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
