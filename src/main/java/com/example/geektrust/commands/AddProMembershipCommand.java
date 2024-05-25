package com.example.geektrust.commands;

import com.example.geektrust.service.IStudentService;

import java.util.List;

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
