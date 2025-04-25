package com.main.Controller;



import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.main.Model.Attendance;
import com.main.Model.Student;
import com.main.Service.AttendanceService;

@Controller
public class AttendanceController {

	private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }
    
    @GetMapping("/dashboard")
    public String showDashboard(HttpSession session, Model model) {
        Student student = (Student) session.getAttribute("loggedStudent");
        if (student != null) {
            model.addAttribute("student", student);
            return "dashboard";
        }
        return "redirect:/login";
    }

    @PostMapping("/dashboard")
    public String markAttendance(HttpSession session, RedirectAttributes redirectAttributes) {
        Student student = (Student) session.getAttribute("loggedStudent");
        if (student != null) {
            String today = java.time.LocalDate.now().toString();

          
            if (attendanceService.hasAttendanceForDate(student.getId(), today)) {
                session.invalidate(); 
                redirectAttributes.addFlashAttribute("error", "Today's attendance has already been marked.");
                return "redirect:/login"; 
            } else {
                Attendance attendance = new Attendance();
                attendance.setStudent(student);
                attendance.setDate(today);
                attendance.setPresent(true);

                attendanceService.markAttendance(attendance);

                session.invalidate(); 
                redirectAttributes.addFlashAttribute("message", "Attendance marked successfully!");
                return "redirect:/login";
            }
        }
        return "redirect:/login"; 
    }


    @GetMapping("/attendance")
    public String viewAttendance(HttpSession session, Model model) {
        Student student = (Student) session.getAttribute("loggedStudent");
        if (student != null) {
            model.addAttribute("attendanceList", attendanceService.getAttendanceByStudent(student.getId()));
            return "attendance";
        }
        return "redirect:/login";
    }
	

}

