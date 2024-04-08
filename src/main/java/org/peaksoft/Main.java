package org.peaksoft;

import org.peaksoft.model.entities.Student;
import org.peaksoft.model.enums.Gender;
import org.peaksoft.model.enums.StudyFormat;
import org.peaksoft.service.impl.StudentService;

public class Main {
    public static void main(String[] args) {
        StudentService studentService = new StudentService();
        studentService.create(new Student("Aidai", 19, StudyFormat.OFFLINE , Gender.FEMALE , null , null ,null));

    }
}
