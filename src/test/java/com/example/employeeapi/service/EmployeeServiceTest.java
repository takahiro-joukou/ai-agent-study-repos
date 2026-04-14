package com.example.employeeapi.service;

import com.example.employeeapi.model.Employee;
import com.example.employeeapi.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void shouldReturnEmployeesSortedByEmployeeID() {
        when(employeeRepository.findAll()).thenReturn(List.of(
                new Employee("E100", "佐藤 次郎", "開発部", "主任", "在籍"),
                new Employee("E050", "山田 太郎", "営業部", "課長", "休職")
        ));

        List<Employee> result = employeeService.getAllEmployees();

        assertEquals(2, result.size());
        assertEquals("E050", result.get(0).getEmployeeID());
        assertEquals("E100", result.get(1).getEmployeeID());
    }
}
