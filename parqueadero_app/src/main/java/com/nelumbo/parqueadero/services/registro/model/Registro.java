package com.nelumbo.parqueadero.services.registro.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class Registro {
    private UUID id;
    private UUID vehiculoId;
    private UUID parqueaderoId;
    private Date entry;
    private Date exit;
    private Double total;
}
