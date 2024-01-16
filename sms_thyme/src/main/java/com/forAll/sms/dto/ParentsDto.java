package com.forAll.sms.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ParentsDto {
    private  Long id;

    @NotEmpty(message= "Parent first name should not be Empty")
    private String firstName;

    @NotEmpty(message= "Parent last name should not be Empty")
    private String lastName;

    @NotEmpty(message= "Parent email address should not be Empty")
    private String email;

    @NotEmpty(message= "Parent User name should not be Empty")
    private String userName;


}
