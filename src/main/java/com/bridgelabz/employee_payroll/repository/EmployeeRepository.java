package com.bridgelabz.employee_payroll.repository;

import com.bridgelabz.employee_payroll.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "select * from employee_payroll, employee_department = id and department=:department", nativeQuery = true)
    List<Employee> findEmployeesByDepartment(String Department);
}
