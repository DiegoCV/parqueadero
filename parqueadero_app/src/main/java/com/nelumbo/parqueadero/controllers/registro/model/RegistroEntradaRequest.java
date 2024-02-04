package com.nelumbo.parqueadero.controllers.registro.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class RegistroEntradaRequest {
    @NotNull
    @Size(min = 6, max = 6, message = "El tamaño permitido para la placa es seis")
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    @JsonProperty("placa")
    private String placa;

    @NotNull
    @JsonProperty("parqueaderoId")
    private UUID parqueaderoId;
}
