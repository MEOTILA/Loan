package org.example.repository;

import org.example.entity.Education;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class EducationRepository {
    public Education save(Session session, Education education) {
        session.persist(education);
        return education;
    }

    public List<Education> findAll(Session session) {
        return session
                .createQuery("from org.example.entity.Education", Education.class)
                .list();
    }

    public Optional<Education> findById(Session session, Long id) {
        return session.byId(Education.class).loadOptional(id);
    }
}
