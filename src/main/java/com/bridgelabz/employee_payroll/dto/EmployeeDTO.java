package com.bridgelabz.employee_payroll.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDTO {

    @Pattern(regexp="^[A-Z]{1}[a-zA-Z\\s]{2,}$", message="Employee name Invalid")
    public String name;

    @Min(value=500, message="Min wage should be more than 500")
    public long salary;

    @Pattern(regexp="male|female",message = "Gender needs to be male or female")
    public String gender;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "startDate should not be empty")
    @PastOrPresent(message = "startDate should be past or today date")
    public LocalDate startDate;

    @NotBlank(message = "note cannot be empty")
    public String note;

    @NotBlank(message = "profilePic cannot be empty")
    public String profilePic;

    @NotNull(message = "department should not be empty")
    public List<String> department;

}
