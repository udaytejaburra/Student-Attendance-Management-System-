package com.main.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.main.Dao.AttendanceDao;
import com.main.Model.Attendance;
@Service
public class AttendanceService {
	 private final AttendanceDao attendanceDao;

	    public AttendanceService(AttendanceDao attendanceDao) {
	        this.attendanceDao = attendanceDao;
	    }

	    public void markAttendance(Attendance attendance) {
	        attendanceDao.save(attendance);
	    }

	    public List<Attendance> getAttendanceByStudent(int studentId) {
	        return attendanceDao.findByStudentId(studentId);
	    }

	    public boolean hasAttendanceForDate(int studentId, String date) {
	        return attendanceDao.existsByStudentIdAndDate(studentId, date);
	    }

	    public List<Attendance> getAllAttendance() {
	        return attendanceDao.findAll();
	    }



}



