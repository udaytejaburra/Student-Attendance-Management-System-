package com.main.Service;

import org.springframework.stereotype.Service;

import com.main.Dao.StudentDao;
import com.main.Model.Student;
@Service
public class StudentService {
	private final StudentDao studentDao;

    public StudentService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public void registerStudent(Student student) {
        studentDao.save(student);
    }

    public Student login(String username, String password) {
        Student student = studentDao.findByUsername(username);
        if (student != null && student.getPassword().equals(password)) {
            return student;
        }
        return null;
    }

}
