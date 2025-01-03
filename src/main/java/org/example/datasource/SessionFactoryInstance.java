package org.example.datasource;

import org.example.entity.Education;
import org.example.entity.Loan;
import org.example.entity.StudentUni;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryInstance {
    public static SessionFactory sessionFactory;

    static {
        sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Education.class)
                .addAnnotatedClass(Loan.class)
                .addAnnotatedClass(StudentUni.class)
                .buildSessionFactory();
    }
}
