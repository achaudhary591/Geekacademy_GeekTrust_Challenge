package com.geektrust.backend.commands;

import com.geektrust.backend.exceptions.NoSuchCommandException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CommandInvokerTest {

    private CommandInvoker commandInvoker;

    private AddProgrammeCommand addProgrammeCommand;
    
    private ApplyCouponCommand applyCouponCommand;

    private AddProMembershipCommand addProMembershipCommand;

    private PrintBillCommand printBillCommand;

    @BeforeEach
    void setUp() {
        
        addProgrammeCommand = Mockito.mock(AddProgrammeCommand.class);
        applyCouponCommand = Mockito.mock(ApplyCouponCommand.class);
        addProMembershipCommand = Mockito.mock(AddProMembershipCommand.class);
        printBillCommand = Mockito.mock(PrintBillCommand.class);

        commandInvoker = new CommandInvoker();
        commandInvoker.register("ADD_PROGRAMME", addProgrammeCommand);
        commandInvoker.register("APPLY_COUPON", applyCouponCommand);
        commandInvoker.register("ADD_PRO_MEMBERSHIP", addProMembershipCommand);
        commandInvoker.register("PRINT_BILL", printBillCommand);
    }

    @DisplayName("addProgrammeCommand method Should Execute Command")
    @Test
    void executeAddProgrammeCommand() throws NoSuchCommandException {
        commandInvoker.executeCommand("ADD_PROGRAMME", new ArrayList<String>());
        verify(addProgrammeCommand, times(1)).execute(new ArrayList<String>());

    }

    @DisplayName("applyCouponCommand method Should Execute Command")
    @Test
    void executeApplyCouponCommand() throws NoSuchCommandException {
        commandInvoker.executeCommand("APPLY_COUPON", new ArrayList<String>());
        verify(applyCouponCommand, times(1)).execute(new ArrayList<String>());

    }

    @DisplayName("addProMembershipCommand method Should Execute Command")
    @Test
    void executeAddProMembershipCommand() throws NoSuchCommandException {
        commandInvoker.executeCommand("ADD_PRO_MEMBERSHIP", new ArrayList<String>());
        verify(addProMembershipCommand, times(1)).execute(new ArrayList<String>());

    }

    @DisplayName("printBillCommand method Should Execute Command")
    @Test
    void executePrintBillCommand() throws NoSuchCommandException {
        commandInvoker.executeCommand("PRINT_BILL", new ArrayList<String>());
        verify(printBillCommand, times(1)).execute(new ArrayList<String>());

    }



    @DisplayName("executeCommand method Should Throw Exception Given Incorrect CommandName and List of tokens")
    @Test
    void executeCommandWithException() {
        assertThrows(NoSuchCommandException.class, () -> commandInvoker.executeCommand("INVALID_COMMAND", new ArrayList<String>()));
    }
}

