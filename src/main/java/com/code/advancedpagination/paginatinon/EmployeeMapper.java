package com.code.advancedpagination.paginatinon;

import org.springframework.stereotype.Service;

@Service
public class EmployeeMapper {
    public Employee toEmployee(Employee employee){
        return Employee.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .gender(employee.getGender())
                .build();
    }
}
