package com.athar.food_reservation.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
public class RegisterRequest {
    @NotEmpty(message = "Username is mandatory")
    @NotBlank(message = "Username is mandatory")
    private String username;
    @NotEmpty(message = "Password is mandatory")
    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "password should be 8 characters long minimum")
    private String password;
    @NotEmpty(message = "Employee Id is mandatory")
    @NotBlank(message = "Employee Id is mandatory")
    private String employeeId;
    @NotEmpty(message = "Phone number is mandatory")
    @NotBlank(message = "Phone number Id is mandatory")
    private String phoneNumber;
    private Set<String> roles;
}
