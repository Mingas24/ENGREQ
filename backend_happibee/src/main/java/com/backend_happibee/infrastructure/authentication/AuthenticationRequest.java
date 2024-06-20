package com.backend_happibee.infrastructure.authentication;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AuthenticationRequest {

    @NotNull
    @Email
    String username;
    @NotNull
    String password;
    
}
