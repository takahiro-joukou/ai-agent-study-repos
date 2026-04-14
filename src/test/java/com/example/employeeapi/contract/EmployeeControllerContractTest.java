package com.example.employeeapi.contract;

import com.example.employeeapi.model.Employee;
import com.example.employeeapi.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerContractTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        employeeRepository.deleteAll();
    }

    @SuppressWarnings("null")
    @Test
    void shouldReturnSortedEmployeeListForAuthorizedUser() throws Exception {
        employeeRepository.saveAll(List.of(
                new Employee("E100", "佐藤 次郎", "開発部", "主任", "在籍"),
                new Employee("E050", "山田 太郎", "営業部", "課長", "在籍")
        ));

        mockMvc.perform(get("/api/employees")
                        .header("Authorization", "Bearer administrator"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employees[0].employeeID").value("E050"))
                .andExpect(jsonPath("$.employees[1].employeeID").value("E100"))
                .andExpect(jsonPath("$.message").doesNotExist());
    }
}
