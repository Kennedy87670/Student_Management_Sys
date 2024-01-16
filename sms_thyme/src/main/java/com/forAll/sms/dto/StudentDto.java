package com.forAll.sms.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDto {

    private  Long id;

    @NotEmpty(message= "Student first name should not be Empty")
    private String firstName;

    @NotEmpty(message= "Student last name should not be Empty")
    private String lastName;

    @NotEmpty(message = "Student UserName should not be empty")
    private String userName;

    @NotEmpty(message = "Student email should not be empty")
    @Email
    private String email;


}
