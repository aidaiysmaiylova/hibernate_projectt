package org.peaksoft.model.entities;

// TODO: 07.10.2023 в классе StudentIdCard должен быть поля(Long id, String identityNumber,
//  LocalDate createDate)

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
@Table(name = "students_id_card")
@FieldDefaults (level = AccessLevel.PRIVATE)
public class StudentIdCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String identityNumber;
    LocalDate createDate;

    public StudentIdCard(int i, String identityNumber, LocalDate now) {
        this.identityNumber = identityNumber;
    }
}
