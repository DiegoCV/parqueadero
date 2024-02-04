package com.nelumbo.parqueadero.controllers.parqueadero.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ParqueaderoRequest {
    private String id;
    private String nombre;
    private String usuario;
    private Double costo;
    private Integer capacidad;
}
