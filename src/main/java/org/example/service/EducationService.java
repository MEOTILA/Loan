package org.example.service;

import org.example.datasource.SessionFactoryInstance;
import org.example.entity.Education;
import org.example.repository.EducationRepository;

import java.util.List;
import java.util.Optional;

public class EducationService {

    EducationRepository educationRepository;
    public EducationService (EducationRepository educationRepository)
    {
        this.educationRepository = educationRepository;
    }

    public Education save(Education education) {
        try (var session = SessionFactoryInstance.sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                educationRepository.save(session, education);
                session.getTransaction().commit();
                return education;
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
    }

    public List<Education> findAll() {
        try (var session = SessionFactoryInstance.sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                var result = educationRepository.findAll(session);
                System.out.println("List of Education:");
                for (Education education : result) {
                    System.out.println("ID: " + education.getId());
                    System.out.println("Year Start: " + education.getYearStart());
                    System.out.println("University Name: " + education.getUniversityName());
                    System.out.println("Uni Type: " + education.getUniType());
                    System.out.println("Education Level: " + education.getEducationLevel());
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

    public Optional<Education> findById(Long id) {
        try (var session = SessionFactoryInstance.sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                Optional<Education> res = educationRepository.findById(session, id);

                if (res.isPresent()) {
                    Education education = res.get();

                    System.out.println("Founded Education:");
                    System.out.println("ID: " + education.getId());
                    System.out.println("Year Start: " + education.getYearStart());
                    System.out.println("University Name: " + education.getUniversityName());
                    System.out.println("Uni Type: " + education.getUniType());
                    System.out.println("Education Level: " + education.getEducationLevel());
                    System.out.println("---------------------------------");
                } else {
                    System.out.println("No Education found with ID: " + id);
                }

                session.getTransaction().commit();
                return res;
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
    }
}
