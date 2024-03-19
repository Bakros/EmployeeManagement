package com.luv2code.springboot.cruddemo.controller;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

       // employee.setFirstName("Seba");
       // System.out.println(employee);

        //System.out.println("All ok");
        //Video 222 2.11 acá voy.

        model.addAttribute("Title","- New Employee");

        return "employee/employee-form";
    }

    @PostMapping("/save")
    public String saveNewEmployee(@ModelAttribute("employee") Employee theEmployee){

        System.out.println("Id new employee created " + employeeService.save(theEmployee).getId());



        return "redirect:/employees/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int id, Model theModel){

        Employee employee = this.employeeService.findById(id);
        theModel.addAttribute("employee", employee);

        theModel.addAttribute("Title","- Update an Employee");

        return "employee/employee-form";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId") int id, Model theModel){

        this.employeeService.deleteById(id);

        return "redirect:/employees/list";
    }
}
