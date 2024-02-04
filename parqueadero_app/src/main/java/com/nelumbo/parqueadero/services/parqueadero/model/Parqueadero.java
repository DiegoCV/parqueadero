package com.nelumbo.parqueadero.services.parqueadero.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class Parqueadero {
    private UUID id;
    private String nombre;
    private UUID usuarioId;
    private Double costo;
    private Integer capacidad;
}
