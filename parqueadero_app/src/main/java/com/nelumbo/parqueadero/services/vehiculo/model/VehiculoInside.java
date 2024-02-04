package com.nelumbo.parqueadero.services.vehiculo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
public class VehiculoInside {
    private UUID id;
    private String placa;
    private Date fechaIngreso;
    private String nombreParqueadero;
}
