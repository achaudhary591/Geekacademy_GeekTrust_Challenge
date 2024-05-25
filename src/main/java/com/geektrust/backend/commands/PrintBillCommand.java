package com.geektrust.backend.commands;

import java.util.List;
import com.geektrust.backend.service.IBillingService;

public class PrintBillCommand implements ICommand {

    private final IBillingService billingService;

    public PrintBillCommand(IBillingService billingService) {
        this.billingService = billingService;
    }

    @Override
    public void execute(List<String> tokens) {
        System.out.println(billingService.calculateBill());      
    }
    
}
