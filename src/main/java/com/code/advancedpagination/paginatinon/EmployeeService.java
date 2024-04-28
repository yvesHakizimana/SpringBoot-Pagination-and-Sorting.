package com.code.advancedpagination.paginatinon;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public PageResponse<Employee> getAllEmployees(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").ascending()  );
        Page<Employee> employeePage = employeeRepository.findAll(pageable);
        List<Employee> employeeList = employeePage.stream()
                .map(employeeMapper::toEmployee)
                .toList();
        return new PageResponse<>(
                employeeList,
                employeePage.getNumber(),
                employeePage.getSize(),
                employeePage.getTotalElements(),
                employeePage.getTotalPages(),
                employeePage.isFirst(),
                employeePage.isLast());
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Employee with id is not present "));
    }

    public void deleteEmployeeById(Integer id) {
        employeeRepository.deleteById(id);
    }
}
