package com.axway.academy.model.daos;

import com.axway.academy.model.entities.ActionRecord;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ActionRecordDao {

    private static final SessionFactory sf = new Configuration().configure()
            .addAnnotatedClass(ActionRecord.class).buildSessionFactory();

    public void saveRecord(ActionRecord record) {
        Transaction transaction = null;
        try (Session session = sf.openSession()) {
            transaction = session.beginTransaction();
            session.persist(record);
            transaction.commit();
        } catch (HibernateException e) {
            e.getMessage();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
