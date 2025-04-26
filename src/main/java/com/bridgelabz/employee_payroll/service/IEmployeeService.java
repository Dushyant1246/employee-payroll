package com.bridgelabz.employee_payroll.service;

import com.bridgelabz.employee_payroll.dto.EmployeeDTO;
import com.bridgelabz.employee_payroll.model.Employee;

import java.util.List;

public interface IEmployeeService {
    List<Employee> getEmployee();

    Employee getEmployeeById(Long empId);

    Employee createEmployee(EmployeeDTO empPayrollDTO);

    Employee updateEmployee(Long empId, EmployeeDTO empPayrollDTO);

    void deleteEmployee(Long empID);

    List<Employee> getEmployeesByDepartment(String department);
}
