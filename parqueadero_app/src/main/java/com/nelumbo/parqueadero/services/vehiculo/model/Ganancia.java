package com.nelumbo.parqueadero.services.vehiculo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class Ganancia {
    private Double hoy;
    private Double semana;
    private Double mes;
    private Double anno;

}
