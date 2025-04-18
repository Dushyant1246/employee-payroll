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
        List<Employee> employeeList = null;
        employeeList = employeeService.getEmployee();
        ResponseDTO responseDTO = new ResponseDTO(employeeList, "Get Call Successful");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/get/{empId}")
    public ResponseEntity<ResponseDTO> getEmployeeData(@PathVariable Long empId){
        Employee emp = null;
        emp = employeeService.getEmployeeById(empId);
        ResponseDTO responseDTO = new ResponseDTO(emp, "Get call for Id successful");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createEmployeeData(@RequestBody EmployeeDTO employeeDTO){
        Employee emp = null;
        emp = employeeService.createEmployee(employeeDTO);
        ResponseDTO responseDTO = new ResponseDTO(emp, "Created Employee Payroll Data successfully");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{empId}")
    public ResponseEntity<ResponseDTO> updateEmployeeData(@RequestBody EmployeeDTO employeeDTO, @PathVariable Long empId){
        Employee emp = null;
        emp = employeeService.updateEmployee(empId, employeeDTO);
        ResponseDTO responseDTO = new ResponseDTO(emp, "Updated Employee Payroll Data successfully");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{empId}")
    public ResponseEntity<ResponseDTO> deleteEmployeeData(@PathVariable Long empId){
        employeeService.deleteEmployee(empId);
        ResponseDTO responseDTO = new ResponseDTO("Deleted ID: " + empId,"Deleted Successfully");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
