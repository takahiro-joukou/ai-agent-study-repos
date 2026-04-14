package com.example.employeeapi.controller;

import com.example.employeeapi.dto.EmployeeListResponse;
import com.example.employeeapi.model.Employee;
import com.example.employeeapi.security.AuthorizationService;
import com.example.employeeapi.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final AuthorizationService authorizationService;

    public EmployeeController(EmployeeService employeeService, AuthorizationService authorizationService) {
        this.employeeService = employeeService;
        this.authorizationService = authorizationService;
    }

    @GetMapping
    public ResponseEntity<EmployeeListResponse> getEmployeeList(
            @RequestHeader(name = "Authorization", required = false) String authorizationHeader) {
        authorizationService.validateAuthorizationHeader(authorizationHeader);
        List<Employee> employees = employeeService.getAllEmployees();
        EmployeeListResponse response = new EmployeeListResponse(employees);
        if (employees.isEmpty()) {
            response.setMessage("現在、登録された従業員は存在しません。");
        }
        return ResponseEntity.ok(response);
    }
}
