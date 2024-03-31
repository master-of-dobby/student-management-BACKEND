package com.naveen.mySBProject1.service;

import com.naveen.mySBProject1.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IStudentService {

    Student addStudent(Student student);

    List<Student> getStudents();

    Student updateStudent(Student student, Long id);

    Student getStudentById(Long id);

    void deleteStudent(Long id);


}
