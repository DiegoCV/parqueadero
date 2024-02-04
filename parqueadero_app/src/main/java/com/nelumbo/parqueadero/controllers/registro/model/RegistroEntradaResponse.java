package com.nelumbo.parqueadero.controllers.registro.model;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class RegistroEntradaResponse {
    private UUID id;
}
