package com.geektrust.backend.appConfig;

import com.geektrust.backend.commands.AddProMembershipCommand;
import com.geektrust.backend.commands.AddProgrammeCommand;
import com.geektrust.backend.commands.ApplyCouponCommand;
import com.geektrust.backend.commands.CommandInvoker;
import com.geektrust.backend.commands.PrintBillCommand;
import com.geektrust.backend.models.Student;
import com.geektrust.backend.repository.BillingRepository;
import com.geektrust.backend.repository.IBillingRepository;
import com.geektrust.backend.repository.IStudentRepository;
import com.geektrust.backend.repository.StudentRepository;
import com.geektrust.backend.service.IBillingService;
import com.geektrust.backend.service.ICartService;
import com.geektrust.backend.service.IDiscountService;
import com.geektrust.backend.service.BillingService;
import com.geektrust.backend.service.CartService;
import com.geektrust.backend.service.DiscountService;
import com.geektrust.backend.service.IStudentService;
import com.geektrust.backend.service.StudentService;


public class ApplicationConfig {

    Student student = new Student();
    private final IStudentRepository studentRepository = new StudentRepository(student);
    private final IBillingRepository billingRepository = new BillingRepository(student);
    private final ICartService cartService = new CartService(studentRepository, billingRepository);
    private final IDiscountService discountService = new DiscountService(studentRepository, billingRepository);
    private final IStudentService studentService = new StudentService(studentRepository);
    private final IBillingService billingService = new BillingService(studentRepository, billingRepository, cartService, discountService);

    private final AddProgrammeCommand addProgrammeCommand = new AddProgrammeCommand(studentService);
    private final ApplyCouponCommand applyCouponCommand = new ApplyCouponCommand(studentService);
    private final AddProMembershipCommand addProMembershipCommand = new AddProMembershipCommand(studentService);
    private final PrintBillCommand printBillCommand = new PrintBillCommand(billingService);

    private final CommandInvoker commandInvoker = new CommandInvoker();

    public CommandInvoker getCommandInvoker(){
        commandInvoker.register("ADD_PROGRAMME",addProgrammeCommand);
        commandInvoker.register("APPLY_COUPON", applyCouponCommand);
        commandInvoker.register("ADD_PRO_MEMBERSHIP", addProMembershipCommand);
        commandInvoker.register("PRINT_BILL", printBillCommand);

        return commandInvoker;
    }

    
}
