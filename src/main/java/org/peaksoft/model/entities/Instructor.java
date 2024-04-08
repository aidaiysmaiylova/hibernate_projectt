package org.peaksoft.model.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.peaksoft.model.enums.Gender;

import java.time.LocalDate;

// TODO: 07.10.2023  в классе Instructor должен быть поля(Long id,String name, String lastName, int age,
//  Gender gender, LocalDate createDate)
//  Relationship: Instructor and Course.На одном курсе может быть несколько инструкторов,
//  но один инструктор не может работать на нескольких курсах
@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
@Table(name="instructors")
@FieldDefaults (level = AccessLevel.PRIVATE)
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String lastname;
    int age;
    Gender gender;
    LocalDate createDate;
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(table = "course_id")
    Course courses;

    public Instructor(String name, String lastname, int age, Gender gender, Course course) {
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.gender = gender;
        this.createDate = createDate;
        this.courses = course;

    }
}
