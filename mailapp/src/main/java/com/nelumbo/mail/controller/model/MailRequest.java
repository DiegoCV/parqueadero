package com.nelumbo.mail.controller.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class MailRequest {
    private String email;
    private String placa;
    private String mensaje;
    private String parqueaderoNombre;
}
