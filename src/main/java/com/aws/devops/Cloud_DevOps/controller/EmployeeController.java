package com.aws.devops.Cloud_DevOps.controller;

import com.aws.devops.Cloud_DevOps.model.Employee;
import com.aws.devops.Cloud_DevOps.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee")
    public Object getEmployee() {
        return employeeService.getEmployee();
    }
}
