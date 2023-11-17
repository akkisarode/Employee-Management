package com.myproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.myproject.entities.Employee;
import com.myproject.service.EmpService;

import jakarta.servlet.http.HttpSession;

@Controller
public class EmpController {

	@Autowired
	EmpService empService;
	
	@GetMapping("/")
	public String home(Model model) {
		
		List<Employee> allEmployeeList = empService.findAllEmployee();
		model.addAttribute("emp",allEmployeeList);
		
		return "index";
	}
	
	@GetMapping("/add_emp")
	public String add_emp() {
		return "add_emp";
	}
	
	@PostMapping("/register")
	public String register(@ModelAttribute Employee emp,HttpSession session){
		
		System.out.println(emp);
		empService.add_emp(emp);
		session.setAttribute("msg","Employee addedd successfully");
		
		return "redirect:/";
	}
	
	@GetMapping("/edit/{id}")
	public String editEmployee(@PathVariable int id, Model model) {
		
		Employee employee = empService.findEmpById(id);
		
		model.addAttribute("emp",employee);
		return "edit_emp";
	}
	
	@PostMapping("/update")
	public String updateEmp(@ModelAttribute Employee e,HttpSession session) {
		
		empService.add_emp(e);
		session.setAttribute("msg","Employee data updated successfully");
		return "redirect:/";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteEmployee(@PathVariable int id,HttpSession session) {
		
		empService.deleteEmployee(id);
		session.setAttribute("msg","Employee deleted successfully");
		return "redirect:/";
	}
}
