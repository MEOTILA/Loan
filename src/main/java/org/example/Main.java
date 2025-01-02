package org.example;


import org.example.entity.Education;
import org.example.entity.Loan;
import org.example.entity.StudentUni;
import org.example.entity.enumss.EducationalLevel;
import org.example.entity.enumss.LoanType;
import org.example.entity.enumss.Pardakht;
import org.example.entity.enumss.universityType;
import org.example.repository.EducationRepository;
import org.example.repository.LoanRepository;
import org.example.repository.StudentRepository;
import org.example.service.EducationService;
import org.example.service.LoanService;
import org.example.service.StudentService;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        StudentRepository studentRepository = new StudentRepository();

        EducationRepository educationRepository = new EducationRepository();
        EducationService educationService = new EducationService(educationRepository);
        StudentService studentService = new StudentService(studentRepository, educationRepository, educationService);


        LoanRepository loanRepository = new LoanRepository();
        LoanService loanService = new LoanService(loanRepository);

        Loan loan1 = Loan.builder().type(LoanType.Shahrieh).pardakht(Pardakht.OncePerStep).build();
        loanService.save(loan1);

        StudentUni student1 = StudentUni.builder().name("Ali").
                lastName("mirzaei").fatherName("hossein").userName("Ali")
                .password("123").loan(loan1).build();

        studentService.save(student1);


        Education education1 = Education.builder().educationLevel(EducationalLevel.Doktora).uniType(universityType.Azad)
                .yearStart(LocalDate.now()).universityName("Science & Research").build();

        educationService.save(education1);






    }
}