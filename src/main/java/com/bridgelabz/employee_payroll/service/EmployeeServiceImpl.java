package com.bridgelabz.employee_payroll.service;

import com.bridgelabz.employee_payroll.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService{
    @Override
    public List<Employee> getEmployee() {
        return List.of();
    }

    @Override
    public Employee getEmployeeById(Long empId) {
        return null;
    }

    @Override
    public Employee createEmployee(Employee emp) {
        return null;
    }

    @Override
    public Employee updateEmployee(Employee emp) {
        return null;
    }

    @Override
    public void deleteEmployee(Long empId) {

    }
}
