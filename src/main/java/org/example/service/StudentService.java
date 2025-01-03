package org.example.service;

import org.example.datasource.SessionFactoryInstance;
import org.example.entity.Education;
import org.example.entity.StudentUni;
import org.example.repository.EducationRepository;
import org.example.repository.StudentRepository;
import org.hibernate.query.sqm.spi.DelegatingSqmSelectionQueryImplementor;

import java.util.List;
import java.util.Optional;

public class StudentService {
    StudentRepository studentRepository;
    EducationRepository educationRepository;
    EducationService educationService = new EducationService(educationRepository);

    public StudentService (StudentRepository studentRepository,
                           EducationRepository educationRepository
    ,EducationService educationService)
    {
        this.studentRepository = studentRepository;
        this.educationRepository = educationRepository;
        this.educationService = educationService;
    }

    public StudentUni save(StudentUni student) {
        try (var session = SessionFactoryInstance.sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                studentRepository.save(session, student);
                session.getTransaction().commit();
                return student;
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
    }

    public List<StudentUni> findAll() {
        try (var session = SessionFactoryInstance.sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                var result = studentRepository.findAll(session);
                System.out.println("List of Students:");
                for (StudentUni student : result) {
                    System.out.println("ID: " + student.getId());
                    System.out.println("Student First Name: " + student.getName());
                    System.out.println("Student Last Name: " + student.getLastName());
                    System.out.println("Father Name: " + student.getFatherName());
                    System.out.println("Mother Name: " + student.getMotherName());
                    System.out.println("BirthDate: " + student.getBirthDate());
                    System.out.println("National ID: " + student.getNationalCode());
                    System.out.println("---------------------------------");
                }
                session.getTransaction().commit();
                return result;
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
    }

    public Optional<StudentUni> findById(Long id) {
        try (var session = SessionFactoryInstance.sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                Optional<StudentUni> res = studentRepository.findById(session, id);

                if (res.isPresent()) {
                    StudentUni student = res.get();

                    System.out.println("Founded Student:");
                    System.out.println("ID: " + student.getId());
                    System.out.println("Student First Name: " + student.getName());
                    System.out.println("National ID: " + student.getNationalCode());
                    System.out.println("---------------------------------");
                } else {
                    System.out.println("No student found with ID: " + id);
                }

                session.getTransaction().commit();
                return res;
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
    }

    public void studentRegister(StudentUni student, Long educationId){
        System.out.println("List of available Education: ");
        if(educationService.findAll()!=null) {

            educationService.findById(educationId);

            //student.setEducation(education);
            save(student);


        }
        else System.out.println("No Education is Founded!");
    }
}
