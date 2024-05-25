package com.example.geektrust.models;

import com.example.geektrust.enums.ProgrammeCategoryEnum;
import com.example.geektrust.helpers.Constants;

public class DiplomaProgramme extends AbstractProgramme {

    public DiplomaProgramme() {
        super(ProgrammeCategoryEnum.DIPLOMA, Constants.DIPLOMA_FEES, Constants.DIPLOMA_DISCOUNT);
    }
}
