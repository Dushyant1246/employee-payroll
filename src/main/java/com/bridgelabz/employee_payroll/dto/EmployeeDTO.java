package com.bridgelabz.employee_payroll.dto;

public class EmployeeDTO {

    private String name;
    private double salary;

    public EmployeeDTO(String name, double salary){
        this.name = name;
        this.salary = salary;
    }

    public EmployeeDTO(){}
    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
