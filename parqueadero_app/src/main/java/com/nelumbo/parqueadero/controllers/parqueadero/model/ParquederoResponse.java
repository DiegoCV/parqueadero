package com.nelumbo.parqueadero.controllers.parqueadero.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ParquederoResponse {
    private String id;
    private String nombre;
    private Double costo;
    private Integer capacidad;
}
