package com.axway.academy.util;

import com.axway.academy.entities.Project;
import com.axway.academy.entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    public static Session getHibernateSession() {

        final SessionFactory sf = new Configuration().configure()
                .addAnnotatedClass(Student.class).addAnnotatedClass(Project.class).buildSessionFactory();

        final Session session = sf.openSession();
        return session;
    }

}