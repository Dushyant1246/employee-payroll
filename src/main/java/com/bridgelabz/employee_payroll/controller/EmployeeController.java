package com.bridgelabz.employee_payroll.controller;

import com.bridgelabz.employee_payroll.dto.EmployeeDTO;
import com.bridgelabz.employee_payroll.dto.ResponseDTO;
import com.bridgelabz.employee_payroll.model.Employee;
import com.bridgelabz.employee_payroll.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @RequestMapping(value = {"", "/", "/get"})
    public ResponseEntity<ResponseDTO> getEmployeeData(){
        List<Employee> employeeList = employeeService.getEmployee();
        ResponseDTO responseDTO = new ResponseDTO(employeeList, "Get Call Successful");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/get/{empId}")
    public ResponseEntity<ResponseDTO> getEmployeeData(@PathVariable Long empId){
        Employee emp = employeeService.getEmployeeById(empId);
        ResponseDTO responseDTO = (emp != null) ? new ResponseDTO(emp, "Get call for Id successful") : new ResponseDTO(null, "Employee with ID " + empId + " not found");
        HttpStatus httpStatus = (emp != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(responseDTO, httpStatus);
    }

    @GetMapping("/department/{department}")
    public ResponseEntity<ResponseDTO> getEmployeesByDepartment(@PathVariable String department){
        List<Employee> employeeList = employeeService.getEmployeesByDepartment(department);
        ResponseDTO respDTO = new ResponseDTO(employeeList, "Get employees by department successful");
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createEmployeeData(@RequestBody EmployeeDTO employeeDTO){
        Employee emp = employeeService.createEmployee(employeeDTO);
        ResponseDTO responseDTO = new ResponseDTO(emp, "Created Employee Payroll Data successfully");
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update/{empId}")
    public ResponseEntity<ResponseDTO> updateEmployeeData(@RequestBody EmployeeDTO employeeDTO, @PathVariable Long empId){
        Employee emp = employeeService.updateEmployee(empId, employeeDTO);
        ResponseDTO responseDTO = (emp != null) ? new ResponseDTO(emp, "Updated Employee Payroll Data successfully") : new ResponseDTO(null, "Employee with ID " + empId + " not found");
        HttpStatus httpStatus = (emp != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(responseDTO, httpStatus);
    }

    @DeleteMapping("/delete/{empId}")
    public ResponseEntity<ResponseDTO> deleteEmployeeData(@PathVariable Long empId){
        Employee empData = employeeService.getEmployeeById(empId);
        if (empData == null) {
            ResponseDTO respDTO = new ResponseDTO(null, "Employee with ID " + empId + " not found");
            return new ResponseEntity<>(respDTO, HttpStatus.NOT_FOUND);
        }
        employeeService.deleteEmployee(empId);
        ResponseDTO respDTO = new ResponseDTO(null, "Employee deleted successfully");
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }
}
