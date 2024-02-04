package com.nelumbo.parqueadero.controllers.authentication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthenticationResponse {
    private String jwt;
}
