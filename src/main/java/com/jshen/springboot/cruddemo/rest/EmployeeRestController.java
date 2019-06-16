package com.jshen.springboot.cruddemo.rest;

import com.jshen.springboot.cruddemo.entity.Employee;
import com.jshen.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    //inject employee DAO
    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService){
        employeeService = theEmployeeService;
    }

    //expose "/employees" and return a list of employees
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    //read single employee
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){
        Employee theEmployee = employeeService.findById(employeeId);

        if(theEmployee == null){
            throw new RuntimeException("Employee id not found - " + employeeId);
        }

        return theEmployee;
    }

    //add a new employee, make sure my sql server is running correctly before sending post request
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee){
        //in case user also passed id in the body, so we set it to 0 first
        theEmployee.setId(0);

        employeeService.save(theEmployee);

        return theEmployee;
    }
}
