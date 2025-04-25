package com.main.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.main.Model.Student;
import com.main.Service.StudentService;

@Controller
public class StudentController {

	private final StudentService studentService;

	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping("/")
	public String home() {
		return "home";
	}

	@GetMapping("/register")
	public String showRegisterForm(Model model) {
		model.addAttribute("Student", new Student());
		return "register";
	}

	@PostMapping("/register")
	public String register(@ModelAttribute Student student) {
		studentService.registerStudent(student);
		return "login";
	}

	@GetMapping("/login")
	public String showLoginForm() {
		return "login";
	}

	@PostMapping("/login")
	public ModelAndView login(@RequestParam String username, @RequestParam String password, HttpSession session) {
	    ModelAndView modelAndView = new ModelAndView();
	    Student student = studentService.login(username, password);

	    if (student != null) {
	        session.setAttribute("loggedStudent", student);

	        modelAndView.addObject("student", student);
	        modelAndView.setViewName("dashboard");
	    } else {
	        modelAndView.setViewName("login");
	        modelAndView.addObject("error", "Invalid username or password");
	    }

	    return modelAndView;
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
	    session.invalidate(); 
	    return "redirect:/login"; 
	}

}


