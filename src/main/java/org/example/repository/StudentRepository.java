package org.example.repository;

import org.example.entity.StudentUni;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class StudentRepository {
    public StudentUni save(Session session, StudentUni student) {
        session.persist(student);
        return student;
    }

    public List<StudentUni> findAll(Session session) {
        return session
                .createQuery("from org.example.entity.StudentUni", StudentUni.class)
                .list();
    }

    public Optional<StudentUni> findById(Session session, Long id) {
        return session.byId(StudentUni.class).loadOptional(id);
    }
}
