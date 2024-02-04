package com.nelumbo.parqueadero.services.vehiculo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
public class Vehiculo {
    private UUID id;
    private String placa;
}
