package com.example.geektrust.commands;

import com.example.geektrust.service.IStudentService;

import java.util.List;

public class AddProgrammeCommand implements ICommand {

    private final IStudentService studentService;

    public AddProgrammeCommand(IStudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void execute(List<String> tokens) {
        String programmeName = tokens.get(1);
        Integer quantity = Integer.parseInt(tokens.get(2));
        try {
            studentService.addProgrammes(programmeName, quantity);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
