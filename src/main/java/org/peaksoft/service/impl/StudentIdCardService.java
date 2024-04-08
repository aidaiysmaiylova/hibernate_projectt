package org.peaksoft.service.impl;

import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.peaksoft.model.entities.StudentIdCard;
import org.peaksoft.service.Service;
import org.peaksoft.util.HibernateUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentIdCardService implements Service<StudentIdCard> {
    @Override
    public void create(StudentIdCard studentIdCard) {
        try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory()) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.persist(studentIdCard);
            session.getTransaction().commit();
        } catch (SessionException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(StudentIdCard studentIdCard, Long id) {
        StudentIdCard oldStudentIdCard = null;
        try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory()) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            oldStudentIdCard = session.get(StudentIdCard.class, id);
            oldStudentIdCard.setCreateDate(LocalDate.now());
            oldStudentIdCard.setIdentityNumber(studentIdCard.getIdentityNumber());
            session.persist(oldStudentIdCard);
            session.getTransaction().commit();
        } catch (SessionException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public List<StudentIdCard> getAll() {
        List<StudentIdCard> studentIdCards = new ArrayList<>();
        try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory()) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            studentIdCards = session.createQuery("FROM StudentIdCard ", StudentIdCard.class).getResultList();
            session.getTransaction().commit();
        } catch (SessionException e) {
            System.out.println(e.getMessage());
        }
        return studentIdCards;
    }

    @Override
    public StudentIdCard getById(Long id) {
        StudentIdCard studentIdCard = null;
        try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory()) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            studentIdCard = session.get(StudentIdCard.class, id);
            session.getTransaction().commit();
        } catch (SessionException e) {
            System.out.println(e.getMessage());
        }
        return studentIdCard;
    }

    @Override
    public String deleteById(Long id) {
        StudentIdCard studentIdCard = null;
        try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory()) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            studentIdCard = session.get(StudentIdCard.class, id);
            session.remove(studentIdCard);
            session.getTransaction().commit();
        } catch (SessionException e) {
            System.out.println(e.getMessage());
        }
        return "Успешно удаленa ID-карта по номеру : " + studentIdCard.getIdentityNumber();
    }
}