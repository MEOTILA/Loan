package org.example.service;

import org.example.datasource.SessionFactoryInstance;
import org.example.entity.Loan;
import org.example.entity.StudentUni;
import org.example.repository.LoanRepository;
import org.example.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

public class LoanService {
    LoanRepository loanRepository;
    public LoanService (LoanRepository loanRepository)
    {
        this.loanRepository = loanRepository;
    }

    public Loan save(Loan loan) {
        try (var session = SessionFactoryInstance.sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                loanRepository.save(session, loan);
                session.getTransaction().commit();
                return loan;
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
    }

    public List<Loan> findAll() {
        try (var session = SessionFactoryInstance.sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                var result = loanRepository.findAll(session);
                System.out.println("List of Loan:");
                for (Loan loan : result) {
                    System.out.println("ID: " + loan.getId());
                    System.out.println("Loan Type: " + loan.getType());
                    System.out.println("Loan type: " + loan.getPardakht());
                    System.out.println("Loan Students: " + loan.getStudents());
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

    public Optional<Loan> findById(Long id) {
        try (var session = SessionFactoryInstance.sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                Optional<Loan> res = loanRepository.findById(session, id);

                if (res.isPresent()) {
                    Loan loan = res.get();

                    System.out.println("Founded Loan:");
                    System.out.println("ID: " + loan.getId());
                    System.out.println("Loan Type: " + loan.getType());
                    System.out.println("Loan type: " + loan.getPardakht());
                    System.out.println("Loan Students: " + loan.getStudents());
                    System.out.println("---------------------------------");
                } else {
                    System.out.println("No Loan found with ID: " + id);
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
