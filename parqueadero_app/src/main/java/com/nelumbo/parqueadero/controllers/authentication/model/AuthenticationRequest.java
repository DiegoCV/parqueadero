package com.nelumbo.parqueadero.controllers.authentication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthenticationRequest {
    private String user;
    private String pass;
}
