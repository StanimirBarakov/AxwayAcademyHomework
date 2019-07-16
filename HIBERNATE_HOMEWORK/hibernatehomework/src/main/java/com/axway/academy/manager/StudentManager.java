package com.axway.academy.manager;

import com.axway.academy.entities.Project;
import com.axway.academy.entities.Student;
import com.axway.academy.util.HibernateUtils;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {


    private static SessionFactory factory = new Configuration().configure().addAnnotatedClass(Student.class)
            .addAnnotatedClass(Project.class).buildSessionFactory();

    public StudentManager() {
    }

    public void addStudent(Student student) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtils.getHibernateSession();
            transaction = session.beginTransaction();
            session.persist(student);
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public List<Student> listStudents() {
        Session session = null;
        Transaction transaction = null;
        List<Student> students = null;
        try {
            session = HibernateUtils.getHibernateSession();
            transaction = session.beginTransaction();
            students = (List<Student>) session.createQuery("from Student").list();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return students;
    }

    public void updateStudent(long id, String newLastName) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtils.getHibernateSession();
            transaction = session.beginTransaction();
            Student student = session.get(Student.class, id);
            student.setLastName(newLastName);
            session.update(student);
            transaction.commit();
            System.out.println("A student with ID " + id + " was UPDATED.");
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public Student getStudent(long id) {
        Session session = null;
        Transaction transaction = null;
        Student student = null;
        try {
            session = HibernateUtils.getHibernateSession();
            transaction = session.beginTransaction();
            student = session.get(Student.class, id);
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return student;
    }

    public boolean deleteStudent(long id) {
        boolean deletedSuccessfully = false;
        Session session = null;
        Transaction transaction = null;
        Student student = null;
        try {
            session =HibernateUtils.getHibernateSession();
            transaction = session.beginTransaction();

            student = session.get(Student.class, id);
            if (student != null) {
                session.delete(student);
                System.out.println("Student with id: " + id + " is deleted!");
                deletedSuccessfully = true;
            }
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return deletedSuccessfully;
    }

    public List<Student> listStudentsYoungerThan(int yearsOld) {
        Session session = factory.openSession();
        Transaction tx = null;
        List students = new ArrayList();
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(Student.class);
            cr.add(Restrictions.lt("age", 20));
            students = cr.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return students;
    }

    public String[] listStudentsYougnerThan(int age) {
        final Session session = HibernateUtils.getHibernateSession();
        final CriteriaBuilder cb = session.getCriteriaBuilder();
        final CriteriaQuery<Student> cr = cb.createQuery(Student.class);
        final Root<Student> root = cr.from(Student.class);
        Path path = root.get("age");
        cr.select(root)
                .where(cb.lt(path,age));
        Query<Student> query = session.createQuery(cr);
        final List<Student> lessThanItemsList = query.getResultList();
        final String lessThanItems[] = new String[lessThanItemsList.size()];
        for (int i = 0; i < lessThanItemsList.size(); i++) {
            lessThanItems[i] = lessThanItemsList.get(i)
                    .getFirsName();
        }
        session.close();
        return lessThanItems;
    }

    public String[] listStudentsWithProjectsMoreThan(int projectCount) {
        final Session session = HibernateUtils.getHibernateSession();
        final CriteriaBuilder cb = session.getCriteriaBuilder();
        final CriteriaQuery<Student> cr = cb.createQuery(Student.class);
        final Root<Student> root = cr.from(Student.class);
        Path path = root.get("projectsCount");
        cr.select(root)
                .where(cb.gt(path,projectCount));
        Query<Student> query = session.createQuery(cr);
        final List<Student> lessThanItemsList = query.getResultList();
        final String lessThanItems[] = new String[lessThanItemsList.size()];
        for (int i = 0; i < lessThanItemsList.size(); i++) {
            lessThanItems[i] = lessThanItemsList.get(i)
                    .getFirsName();
        }
        session.close();
        return lessThanItems;
    }

}


