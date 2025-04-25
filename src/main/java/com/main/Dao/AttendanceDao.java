package com.main.Dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.main.Model.Attendance;

@Repository
public class AttendanceDao {

	 private final HibernateTemplate hibernateTemplate;

	    public AttendanceDao(HibernateTemplate hibernateTemplate) {
	        this.hibernateTemplate = hibernateTemplate;
	    }

	    @Transactional
	    public void save(Attendance attendance) {
	        hibernateTemplate.save(attendance);
	    }

	    public List<Attendance> findByStudentId(int studentId) {
	        String query = "from Attendance where student.id = :studentId";
	        return (List<Attendance>) hibernateTemplate.findByNamedParam(query, "studentId", studentId);
	    }
	    
	    @SuppressWarnings("unchecked")
		public List<Attendance> findAll() {
	        return (List<Attendance>) hibernateTemplate.find("from Attendance");
	    }


	    public boolean existsByStudentIdAndDate(int studentId, String date) {
	        String query = "select count(a) from Attendance a where a.student.id = :studentId and a.date = :date";
	        Long count = (Long) hibernateTemplate.findByNamedParam(query, new String[]{"studentId", "date"}, new Object[]{studentId, date}).get(0);
	        return count > 0;
	    }
	}


