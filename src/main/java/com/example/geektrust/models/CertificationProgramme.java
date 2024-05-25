package com.example.geektrust.models;

import com.example.geektrust.enums.ProgrammeCategoryEnum;
import com.example.geektrust.helpers.Constants;

public class CertificationProgramme extends AbstractProgramme {
    public CertificationProgramme() {
        super(ProgrammeCategoryEnum.CERTIFICATION, Constants.CERTIFICATION_FEES, Constants.CERTIFICATION_DISCOUNT);
    }
}