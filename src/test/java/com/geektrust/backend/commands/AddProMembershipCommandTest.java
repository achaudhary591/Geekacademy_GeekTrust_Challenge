package com.geektrust.backend.commands;

import com.geektrust.backend.service.IStudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collections;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AddProMembershipCommandTest {

    private ICommand addProMembershipCommand;

    private IStudentService studentService;

    @BeforeEach
    void setUp() {
        studentService = Mockito.mock(IStudentService.class);
        addProMembershipCommand = new AddProMembershipCommand(studentService);
    }

    @Test
    void execute() {
        addProMembershipCommand.execute(Collections.emptyList());
        verify(studentService, times(1)).setProMembershipPlan();
    }
}
