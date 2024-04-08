package org.peaksoft.service.impl;

import org.hibernate.*;
import org.hibernate.annotations.ColumnDefault;
import org.peaksoft.model.entities.Course;
import org.peaksoft.model.entities.Instructor;
import org.peaksoft.service.Service;
import org.peaksoft.util.HibernateUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CourseService implements Service<Course> {
    @Override
    public void create(Course course) {
        try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory()) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.persist(course);
            session.getTransaction().commit();
        } catch (SessionException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Course course, Long id) {
        Course oldCourse = null;
        try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory()) {
            Session session = sessionFactory.openSession();
            session.getTransaction();
            oldCourse = session.get(Course.class, id);
            oldCourse.setName(course.getName());
            oldCourse.setDurationMonth(course.getDurationMonth());
            oldCourse.setLocalDate(LocalDate.now());
            session.persist(oldCourse);
            session.getTransaction().commit();
        } catch (SessionException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Course> getAll() {
        List<Course> courses = new ArrayList<>();
        try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory()) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            courses = session.createQuery("FROM Course ", Course.class).getResultList();
            session.getTransaction().commit();
        } catch (SessionException e) {
            System.out.println(e.getMessage());
        }
        return courses;
    }

    @Override
    public Course getById(Long id) {
        Course course = null;
        try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory()) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            course = session.get(Course.class, id);
            session.getTransaction().commit();
        } catch (SessionException e) {
            System.out.println(e.getMessage());
        }
        return course;
    }

    @Override
    public String deleteById(Long id) {
        Course course = null;
        try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory()) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            course = session.get(Course.class, id);
            session.remove(course);
            session.getTransaction().commit();
        } catch (SessionException e) {
            System.out.println(e.getMessage());
        }
        return "Курс по названии : " + course.getName() + " успешно удален !";
    }
}