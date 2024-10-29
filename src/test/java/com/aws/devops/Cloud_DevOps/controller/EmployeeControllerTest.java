package com.aws.devops.Cloud_DevOps.controller;

import com.aws.devops.Cloud_DevOps.model.Employee;
import com.aws.devops.Cloud_DevOps.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class EmployeeControllerTest {

  @Mock EmployeeService employeeService;

  @InjectMocks EmployeeController employeeController;

  @Autowired WebApplicationContext webApplicationContext;

  private MockMvc mockMvc;

  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  public void getEmployeeTest() throws Exception {
    List<Employee> empList =
        Arrays.asList(
            new Employee(1, "sumit", 12.0),
            new Employee(2, "Amit", 36.00),
            new Employee(3, "Saket", 693.00));

    when(employeeService.getEmployee()).thenReturn(empList);
    mockMvc
        .perform(get("/employee").accept(MediaType.ALL))
        .andExpect(status().isOk())
        .andExpect(
            content()
                .json(
                    "[{\"empId\":1,\"empName\":\"sumit\",\"empSalary\":12.0},{\"empId\":2,\"empName\":\"Amit\",\"empSalary\":36.0},{\"empId\":3,\"empName\":\"Saket\",\"empSalary\":693.0}]"));
  }
}
