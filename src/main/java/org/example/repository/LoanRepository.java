package org.example.repository;

import org.example.entity.Loan;
import org.example.entity.StudentUni;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class LoanRepository {
    public Loan save(Session session, Loan loan) {
        session.persist(loan);
        return loan;
    }

    public List<Loan> findAll(Session session) {
        return session
                .createQuery("from org.example.entity.Loan", Loan.class)
                .list();
    }

    public Optional<Loan> findById(Session session, Long id) {
        return session.byId(Loan.class).loadOptional(id);
    }
}
