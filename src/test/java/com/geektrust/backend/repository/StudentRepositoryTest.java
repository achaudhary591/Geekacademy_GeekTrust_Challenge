package com.geektrust.backend.repository;

import com.geektrust.backend.enums.ProgrammeCategoryEnum;
import com.geektrust.backend.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentRepositoryTest {

    private StudentRepository studentRepository;
    private Student student;
    private CertificationProgramme certificationProgramme;
    private DegreeProgramme degreeProgramme;
    private DiplomaProgramme diplomaProgramme;

    @BeforeEach
    public void setup() {
        student = Mockito.mock(Student.class);
        certificationProgramme = Mockito.mock(CertificationProgramme.class);
        degreeProgramme = Mockito.mock(DegreeProgramme.class);
        diplomaProgramme = Mockito.mock(DiplomaProgramme.class);

        studentRepository = new StudentRepository(student, certificationProgramme, degreeProgramme, diplomaProgramme);
    }

    @Test
    public void testAddProgramsToCart() {
        studentRepository.addProgramsToCart(ProgrammeCategoryEnum.CERTIFICATION, 2);
        Mockito.verify(certificationProgramme, Mockito.times(1)).addProgram(2);

        studentRepository.addProgramsToCart(ProgrammeCategoryEnum.DEGREE, 3);
        Mockito.verify(degreeProgramme, Mockito.times(1)).addProgram(3);

        studentRepository.addProgramsToCart(ProgrammeCategoryEnum.DIPLOMA, 4);
        Mockito.verify(diplomaProgramme, Mockito.times(1)).addProgram(4);
    }

    @Test
    public void testGetTotalProgrammeCount() {
        when(certificationProgramme.getProgrammeCount()).thenReturn(2);
        when(degreeProgramme.getProgrammeCount()).thenReturn(3);
        when(diplomaProgramme.getProgrammeCount()).thenReturn(4);

        Integer totalProgrammeCount = studentRepository.getTotalProgrammeCount();

        assertEquals(9, totalProgrammeCount);
    }
}
