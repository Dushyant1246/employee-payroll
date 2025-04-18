package com.bridgelabz.employee_payroll.service;

import com.bridgelabz.employee_payroll.model.Employee;

import java.util.List;

public interface IEmployeeService {
    List<Employee> getEmployee();
    Employee getEmployeeById(Long empId);
    Employee createEmployee(Employee emp);
    Employee updateEmployee(Employee emp);
    void deleteEmployee(Long empId);
}
