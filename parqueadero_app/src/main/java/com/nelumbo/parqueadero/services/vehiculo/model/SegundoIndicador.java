package com.nelumbo.parqueadero.services.vehiculo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SegundoIndicador {
    private String parqueadero;
    private String placa;
    private Long cantidad;
}
