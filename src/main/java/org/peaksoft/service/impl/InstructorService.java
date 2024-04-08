package org.peaksoft.service.impl;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.*;
import org.peaksoft.model.entities.Instructor;
import org.peaksoft.service.Service;
import org.peaksoft.util.HibernateUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

public class InstructorService implements Service<Instructor> {
    @Override
    public void create(Instructor instructor) {
        try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory()) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.persist(instructor);
            session.getTransaction().commit();
        } catch (SessionException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Instructor instructor, Long id) {
        Instructor oldInstructor = null;
        try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory()) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            oldInstructor = session.get(Instructor.class, id);
            oldInstructor.setName(instructor.getName());
            oldInstructor.setLastname(instructor.getLastname());
            oldInstructor.setAge(instructor.getAge());
            oldInstructor.setCourses(instructor.getCourses());
            oldInstructor.setGender(instructor.getGender());
            oldInstructor.setCreateDate(LocalDate.now());
            session.persist(oldInstructor);
            session.getTransaction().commit();
        } catch (SessionException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Instructor> getAll() {
        List<Instructor> instructors = new ArrayList<>();
        try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory()) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            instructors = session.createQuery("FROM Instructor ", Instructor.class).getResultList();
            session.getTransaction().commit();
        } catch (SessionException e) {
            System.out.println(e.getMessage());
        }
        return instructors;
    }

    @Override
    public Instructor getById(Long id) {
        Instructor instructor = null;
        try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory()) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            instructor = session.get(Instructor.class, id);
            session.getTransaction().commit();
        } catch (SessionException e) {
            System.out.println(e.getMessage());
        }
        return instructor;
    }

    @Override
    public String deleteById(Long id) {
        Instructor instructor = null;
        try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory()) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            instructor = session.get(Instructor.class, id);
            session.remove(instructor);
            session.getTransaction().commit();
        } catch (SessionException e) {
            System.out.println(e.getMessage());
        }
        return "Инструктор по имени " + instructor.getName() + instructor.getLastname() + " Успешно удален!";
    }
}