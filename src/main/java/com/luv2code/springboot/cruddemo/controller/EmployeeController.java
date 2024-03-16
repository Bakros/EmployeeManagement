package com.luv2code.springboot.cruddemo.controller;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService theEmployeeService){
        this.employeeService = theEmployeeService;
    }

    //http://localhost:8080/employees/list
    @GetMapping("/list")
    public String listEmployee(Model model){
        //get the employees from DB
        List<Employee> allEmployees = employeeService.findAll();

        //Add that to the spring model
        model.addAttribute("employees", allEmployees);

        return "list-employees";
    }


    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){

        Employee employee = new Employee();

        model.addAttribute("employee",employee);

        //System.out.println("All ok");
        //Video 222 2.11 acá voy.

        return "employee/employee-form";
    }
}
