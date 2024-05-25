package com.example.geektrust.repository;

import com.example.geektrust.enums.ProgrammeCategoryEnum;
import com.example.geektrust.models.CertificationProgramme;
import com.example.geektrust.models.DegreeProgramme;
import com.example.geektrust.models.DiplomaProgramme;
import com.example.geektrust.models.Student;
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
    private CertificationProgramme certificationProgramme;
    private DegreeProgramme degreeProgramme;
    private DiplomaProgramme diplomaProgramme;

    @BeforeEach
    public void setup() {
        Student student = Mockito.mock(Student.class);
        certificationProgramme = Mockito.mock(CertificationProgramme.class);
        degreeProgramme = Mockito.mock(DegreeProgramme.class);
        diplomaProgramme = Mockito.mock(DiplomaProgramme.class);

        studentRepository = new StudentRepository(student, certificationProgramme, degreeProgramme, diplomaProgramme);
    }

    @Test
    public void testAddProgrammesToCart() {
        studentRepository.addProgrammesToCart(ProgrammeCategoryEnum.CERTIFICATION, 2);
        Mockito.verify(certificationProgramme, Mockito.times(1)).addProgramme(2);

        studentRepository.addProgrammesToCart(ProgrammeCategoryEnum.DEGREE, 3);
        Mockito.verify(degreeProgramme, Mockito.times(1)).addProgramme(3);

        studentRepository.addProgrammesToCart(ProgrammeCategoryEnum.DIPLOMA, 4);
        Mockito.verify(diplomaProgramme, Mockito.times(1)).addProgramme(4);
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
