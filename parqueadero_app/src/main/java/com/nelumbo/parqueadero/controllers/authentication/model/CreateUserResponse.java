package com.nelumbo.parqueadero.controllers.authentication.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class CreateUserResponse {
    private String id;
    private String email;
}
