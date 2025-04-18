package com.bridgelabz.employee_payroll.service;

import com.bridgelabz.employee_payroll.dto.EmployeeDTO;
import com.bridgelabz.employee_payroll.model.Employee;

import java.util.List;

public interface IEmployeeService {
    List<Employee> getEmployee();
    Employee getEmployeeById(Long empId);
    Employee createEmployee(EmployeeDTO employeeDTO);
    Employee updateEmployee(Long employeeId, EmployeeDTO employeeDTO);
    void deleteEmployee(Long empId);
}
