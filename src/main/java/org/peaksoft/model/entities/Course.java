package org.peaksoft.model.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.lang.invoke.LambdaConversionException;
import java.time.LocalDate;
import java.util.List;

// TODO: 07.10.2023 в классе Course должен быть поля(Long id, String courseName,
//  String durationMonth, LocalDate createDate)

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
@Table(name = "coureses")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String durationMonth;
    LocalDate localDate;
    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Instructor> instructors;
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST})
    List<Student> students;

    public Course(String name, String durationMonth, LocalDate localDate) {
        this.name = name;
        this.durationMonth = durationMonth;
        this.localDate = localDate;
    }
}
