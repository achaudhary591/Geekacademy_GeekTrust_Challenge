package com.geektrust.backend.commands;

import java.util.List;
import com.geektrust.backend.service.IStudentService;

public class AddProMembershipCommand implements ICommand {

    private final IStudentService studentService;

    public AddProMembershipCommand(IStudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void execute(List<String> tokens) {
        studentService.setProMembershipPlan(); 
    }
    
}
