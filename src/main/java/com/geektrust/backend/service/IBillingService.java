package com.geektrust.backend.service;

import com.geektrust.backend.dao.BillPrintDao;


public interface IBillingService {
    BillPrintDao calculateProgrammeBill();
}
