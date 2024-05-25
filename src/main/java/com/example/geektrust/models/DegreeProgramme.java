package com.example.geektrust.models;

import com.example.geektrust.enums.ProgrammeCategoryEnum;
import com.example.geektrust.helpers.Constants;

public class DegreeProgramme extends AbstractProgramme {
    public DegreeProgramme() {
        super(ProgrammeCategoryEnum.DEGREE, Constants.DEGREE_FEES, Constants.DEGREE_DISCOUNT);
    }
}