package com.main.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.main.Model.Admin;
import com.main.Model.Attendance;
import com.main.Model.Student;
import com.main.Service.AdminService;
import com.main.Service.AttendanceService;

@Controller
public class AdminController {
	
    private final AdminService adminService;
    private final AttendanceService attendanceService;

    public AdminController(AdminService adminService, AttendanceService attendanceService) {
        this.adminService = adminService;
        this.attendanceService = attendanceService;
    }

    // Admin Login Page
    @GetMapping("/admin_login")
    public String showAdminLoginForm() {
        return "admin_login";
    }

    // Handle Admin Login
    @PostMapping("/admin_login")
    public ModelAndView adminLogin(@RequestParam String username, @RequestParam String password, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        Admin admin = adminService.login(username, password);

        if (admin != null) {
            session.setAttribute("loggedAdmin", admin);
            modelAndView.setViewName("admin_dashboard");
        } else {
            modelAndView.setViewName("admin_login");
            modelAndView.addObject("error", "Invalid username or password");
        }

        return modelAndView;
    }

    // Admin Dashboard
    @GetMapping("/admin_dashboard")
    public String showAdminDashboard(HttpSession session) {
        Admin admin = (Admin) session.getAttribute("loggedAdmin");
        if (admin != null) {
            return "admin_dashboard";
        }
        return "redirect:/admin_login"; // Fixed redirect issue
    }

    // Show Attendance Marking Page
    @GetMapping("/mark_attendance")
    public String showMarkAttendanceForm(HttpSession session) {
        if (session.getAttribute("loggedAdmin") != null) {
            return "mark_attendance";
        }
        return "redirect:/admin_login";
    }

    // Mark Student Attendance
    @PostMapping("/mark_attendance")
    public String markAttendance(@RequestParam int studentId, @RequestParam String date, @RequestParam boolean present, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("loggedAdmin");
        if (admin != null) {
            Attendance attendance = new Attendance();
            Student student = new Student();
            student.setId(studentId); // Ensure Student has a setter for ID
            
            attendance.setStudent(student);
            attendance.setDate(date);
            attendance.setPresent(present);
            
            attendanceService.markAttendance(attendance);
            return "redirect:/admin_dashboard"; // Fixed redirect issue
        }
        return "redirect:/admin_login"; 
    }

    // View Attendance for All Students
    @GetMapping("/view_attendance")
    public String viewAllAttendance(Model model, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("loggedAdmin");
        if (admin != null) {
            List<Attendance> attendanceList = attendanceService.getAllAttendance(); 
            model.addAttribute("attendanceList", attendanceList);
            return "view_attendance"; // This should match the JSP file name
        }
        return "redirect:/admin_login";
    }

    // Admin Logout
    @GetMapping("/admin_logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin_login";
    }
}
