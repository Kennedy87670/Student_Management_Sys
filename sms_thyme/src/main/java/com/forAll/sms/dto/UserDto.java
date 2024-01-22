package com.forAll.sms.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Integer id;
    @NotEmpty(message= "First Name should not be Empty")
    private String firstName;
    @NotEmpty(message= "Last Name should not be Empty")
    private String lastName;
    @NotEmpty(message= "UserName should not be Empty")
    private String userName;
    @NotEmpty(message= "Email should not be Empty")
    @Email
    private String email;
    @NotEmpty(message= "Password should not be Empty")
    private String password;
    @NotEmpty(message= "UserType should not be Empty")
    private String userType;
}
