package org.peaksoft.service.impl;

import org.hibernate.*;
import org.peaksoft.model.entities.Student;
import org.peaksoft.service.Service;
import org.peaksoft.util.HibernateUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentService implements Service<Student> {
    @Override
    public void create(Student student) {
        try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory()) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.persist(student);
            session.getTransaction().commit();
            System.out.println("Student saved successfully!");
        } catch (SessionException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void update(Student student, Long id) {
        Student oldStudent = null;
        try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory()) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            oldStudent = session.get(Student.class, id);
            oldStudent.setName(student.getName());
            oldStudent.setAge(student.getAge());
            oldStudent.setStudentIdCard(student.getStudentIdCard());
            oldStudent.setGender(student.getGender());
            oldStudent.setCourses(student.getCourses());
            oldStudent.setCreateDate(LocalDate.now());
            oldStudent.setStudyFormat(student.getStudyFormat());
            session.persist(oldStudent);
            session.beginTransaction().commit();
        } catch (SessionException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public List<Student> getAll() {
        List<Student> students = new ArrayList<>();
        try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory()) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            students = session.createQuery("FROM Student ", Student.class).getResultList();
            session.getTransaction().commit();
        } catch (SessionException e) {
            System.out.println(e.getMessage());
        }
        return students;
    }

    @Override
    public Student getById(Long id) {
        Student student = null;
        try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory()) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            student = session.get(Student.class, id);
            session.getTransaction().commit();
        } catch (SessionException e) {
            System.out.println(e.getMessage());
        }
        return student;
    }

    @Override
    public String deleteById(Long id) {
        Student student = null;
        try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory()) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            student = session.get(Student.class, id);
            session.remove(student);
            session.getTransaction().commit();
        } catch (SessionException e) {
            System.out.println(e.getMessage());
        }
        return "Студент по имени" + student.getName() + " успешно удален!";
    }
}