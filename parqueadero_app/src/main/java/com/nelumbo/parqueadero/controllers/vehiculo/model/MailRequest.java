package com.nelumbo.parqueadero.controllers.vehiculo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@AllArgsConstructor
@Getter
@ToString
public class MailRequest {
    private String email;
    private String placa;
    private String mensaje;
    private UUID parqueaderoId;
}
