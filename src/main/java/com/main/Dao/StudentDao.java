package com.main.Dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.main.Model.Student;

@Repository
public class StudentDao {
	private final HibernateTemplate hibernateTemplate;

	public StudentDao(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Transactional
	public void save(Student student) {
		hibernateTemplate.save(student);
	}

	public Student findByUsername(String username) {
		String query = "from Student where username = :username";
		List<Student> students = (List<Student>) hibernateTemplate.findByNamedParam(query, "username", username);
		return students.isEmpty() ? null : students.get(0);
	}

}
