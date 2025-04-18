package com.bridgelabz.employee_payroll.service;

import com.bridgelabz.employee_payroll.dto.EmployeeDTO;
import com.bridgelabz.employee_payroll.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService{

    private List<Employee> employeeList = new ArrayList<>();

    @Override
    public List<Employee> getEmployee() {
        return employeeList;
    }

    @Override
    public Employee getEmployeeById(Long empId) {
        return employeeList.get((int) (empId - 1));
    }

    @Override
    public Employee createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = null;
        employee = new Employee((long) employeeList.size() + 1, employeeDTO);
        employeeList.add(employee);
        return employee;
    }

    @Override
    public Employee updateEmployee(Long employeeId, EmployeeDTO employeeDTO) {
        Employee employee = null;
        employee = new Employee(employeeId);
        employee.setName(employeeDTO.getName());
        employee.setSalary(employeeDTO.getSalary());
        employeeList.set((int) (employeeId - 1), employee);
        return employee;
    }

    @Override
    public void deleteEmployee(Long empId) {
        employeeList.remove((int) (empId - 1));
    }
}
