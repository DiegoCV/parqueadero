package com.nelumbo.parqueadero.services.vehiculo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PrimerIndicador {
    private String placa;
    private Long cantidad;
}
