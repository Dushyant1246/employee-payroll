package com.bridgelabz.employee_payroll.service;

import com.bridgelabz.employee_payroll.dto.EmployeeDTO;
import com.bridgelabz.employee_payroll.model.Employee;
import com.bridgelabz.employee_payroll.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EmployeeService implements IEmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getEmployee() {
        log.debug("Fetching all Employee Records...");
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long employeeId) {
        log.debug("Fetching Employee by ID {}", employeeId);
        return employeeRepository.findById(employeeId).orElseThrow();
    }

    @Override
    public Employee createEmployee(EmployeeDTO employeeDTO) {
        log.debug("Creating new Employee...");
        Employee employee = new Employee(employeeDTO);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Long employeeId, EmployeeDTO employeeDTO) {
        log.debug("Updating employee with ID {}", employeeId);
        Employee employee = new Employee(employeeId, employeeDTO);
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        log.debug("Deleting Employee with ID {}", employeeId);
        employeeRepository.deleteById(employeeId);
    }

    @Override
    public List<Employee> getEmployeesByDepartment(String department) {
        log.debug("Fetching Employees in Department {}", department);
        return employeeRepository.findEmployeesByDepartment(department);
    }
}
