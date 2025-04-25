package com.main.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.main.Dao.AdminDao;
import com.main.Model.Admin;
import com.main.Model.Attendance;

@Service
public class AdminService {
	
	 private final AdminDao adminDao;
	

	    public AdminService(AdminDao adminDao) {
	        this.adminDao = adminDao;
	    }

	    public void registerAdmin(Admin admin) {
	        adminDao.save(admin);
	    }
	    
	 

	    public Admin login(String username, String password) {
	        Admin admin = adminDao.findByUsername(username);
	        if (admin != null && admin.getPassword().equals(password)) {
	            return admin;
	        }
	        return null;
	    }

}
