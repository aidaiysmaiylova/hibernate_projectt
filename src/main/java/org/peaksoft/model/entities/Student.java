package org.peaksoft.model.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.peaksoft.model.enums.Gender;
import org.peaksoft.model.enums.StudyFormat;

import java.time.LocalDate;
import java.util.List;

// TODO: 07.10.2023 в классе Student должен быть поля (Long id, String name, int age,
//  StudyFormat studyFormat(enum),Gender gender, LocalDate createDate)
//  Relationships:
//  1. Student and StudentIdCard. У каждого Студента должна быть только одна машина
//  2. Student and Course. Студент может обучаться на нескольких курсах, и на одном курсе может быть несколько студентов
@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
@Table(name = "students")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    int age;
    StudyFormat studyFormat;
    Gender gender;
    LocalDate createDate;
    @OneToOne
    StudentIdCard studentIdCard;
    @ManyToMany
    List<Course> courses;

    public Student(String name, int age, StudyFormat studyFormat, Gender gender, LocalDate createDate, StudentIdCard
            studentIdCard, List<Course> courses) {
        this.name = name;
        this.age = age;
        this.studyFormat = studyFormat;
        this.gender = gender;
        this.createDate = createDate;
        this.studentIdCard = studentIdCard;
        this.courses = courses;
    }


}

