package com.aws.devops.Cloud_DevOps.service;

import com.aws.devops.Cloud_DevOps.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeService {

    public List<Employee> getEmployee(){
        return Arrays.asList(new Employee(1, "sumit", 12.0), new Employee(2, "Amit", 36.00), new Employee(3, "Saket", 693.00));

    }
}
