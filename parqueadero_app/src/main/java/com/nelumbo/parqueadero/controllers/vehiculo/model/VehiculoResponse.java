package com.nelumbo.parqueadero.controllers.vehiculo.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.UUID;

@Builder
@Getter
public class VehiculoResponse {
    private UUID id;
    private String placa;
    private Date fechaIngreso;
    private String nombreParqueadero;
}
