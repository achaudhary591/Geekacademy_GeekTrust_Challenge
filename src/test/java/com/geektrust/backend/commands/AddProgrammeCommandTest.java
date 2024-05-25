package com.geektrust.backend.commands;

import com.geektrust.backend.service.IStudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AddProgrammeCommandTest {

    private ICommand addProgrammeCommand;

    private IStudentService studentService;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        studentService = Mockito.mock(IStudentService.class);
        addProgrammeCommand = new AddProgrammeCommand(studentService);
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void execute() {
        addProgrammeCommand.execute(Arrays.asList("ADD_PROGRAMME", "DEGREE", "2"));
        verify(studentService, times(1)).addProgrammes("DEGREE", 2);
    }

   
}
