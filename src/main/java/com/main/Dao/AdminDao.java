package com.main.Dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.main.Model.Admin;
import com.main.Model.Attendance;
@Repository
public class AdminDao {

	 private final HibernateTemplate hibernateTemplate;

	    public AdminDao(HibernateTemplate hibernateTemplate) {
	        this.hibernateTemplate = hibernateTemplate;
	    }

	    @Transactional
	    public void save(Admin admin) {
	        hibernateTemplate.save(admin);
	    }
	    



	    public Admin findByUsername(String username) {
	        String query = "from Admin where username = :username";
	        List<Admin> admins = (List<Admin>) hibernateTemplate.findByNamedParam(query, "username", username);
	        return admins.isEmpty() ? null : admins.get(0);
	    }
	
	
	
}
