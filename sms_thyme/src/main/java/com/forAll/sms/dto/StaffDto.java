package com.forAll.sms.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffDto {

    private  Long id;

    @NotEmpty(message= "Staff first name should not be Empty")
    private String firstName;

    @NotEmpty(message= "Staff last name should not be Empty")
    private String lastName;

    @NotEmpty(message= "Staff email address should not be Empty")
    private String email;

    @NotEmpty(message= "Staff User name should not be Empty")
    private String userName;
}
