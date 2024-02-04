package com.nelumbo.parqueadero.services.mail.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class Mail {
    private String email;
    private String placa;
    private String mensaje;
    private String parqueaderoNombre;
}
