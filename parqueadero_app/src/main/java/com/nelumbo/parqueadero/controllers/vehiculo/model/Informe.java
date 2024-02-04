package com.nelumbo.parqueadero.controllers.vehiculo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Informe {
    private String usuario;

    @JsonCreator
    public Informe(@JsonProperty("usuario") String usuario) {
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }
}
