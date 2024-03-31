package com.naveen.mySBProject1.service;

import com.naveen.mySBProject1.exception.StudentAlreadyExistsException;
import com.naveen.mySBProject1.exception.StudentNotFoundException;
import com.naveen.mySBProject1.model.Student;
import com.naveen.mySBProject1.repo.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements IStudentService{

    @Autowired
    private StudentRepo studentRepo;

    @Override
    public Student addStudent(Student student) {

        if(studentAlreadyExists(student.getEmail())){
            throw new StudentAlreadyExistsException(student.getEmail() + " already exists!");
        }

        return studentRepo.save(student);
    }

    private boolean studentAlreadyExists(String email) {
        return studentRepo.findByEmail(email).isPresent();
    }

    @Override
    public List<Student> getStudents() {
        return studentRepo.findAll();
    }

    @Override
    public Student updateStudent(Student student, Long id) {

        return studentRepo.findById(id).map(st -> {
            st.setFirstName(student.getFirstName());
            st.setLastName(student.getLastName());
            st.setEmail(student.getEmail());
            st.setDepartment(student.getDepartment());

            return studentRepo.save(st);
        }).orElseThrow(() -> new StudentNotFoundException("Sorry! this student NOT FOUND"));
    }

    @Override
    public Student getStudentById(Long id) {

        return studentRepo.findById(id)
                       .orElseThrow(() -> new StudentNotFoundException("Sorry, student NOT FOUND!"));
    }

    @Override
    public void deleteStudent(Long id) {
        if(!studentRepo.existsById(id)){
            throw new StudentNotFoundException("Sorry, Student NOT FOUND!");
        }

        studentRepo.deleteById(id);
    }
}
