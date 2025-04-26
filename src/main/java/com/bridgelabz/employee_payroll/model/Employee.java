package com.bridgelabz.employee_payroll.model;

import com.bridgelabz.employee_payroll.dto.EmployeeDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@Table(name = "employee_payroll")
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long employeeId;
    private String name;
    private long salary;
    private LocalDate startDate;
    private String note;
    private String profilePic;

    @ElementCollection
    @CollectionTable(name = "employee_department", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "department")
    private List<String> departments;


    public Employee(EmployeeDTO employeeDTO) {
        this.name = employeeDTO.getName();
        this.salary = employeeDTO.getSalary();
        this.startDate = employeeDTO.getStartDate();
        this.note = employeeDTO.getNote();
        this.profilePic = employeeDTO.getProfilePic();
    }

    public Employee(Long employeeId, EmployeeDTO employeeDTO) {
        this.employeeId = employeeId;
        this.name = employeeDTO.getName();
        this.salary = employeeDTO.getSalary();
        this.startDate = employeeDTO.getStartDate();
        this.note = employeeDTO.getNote();
        this.profilePic = employeeDTO.getProfilePic();
    }

}
