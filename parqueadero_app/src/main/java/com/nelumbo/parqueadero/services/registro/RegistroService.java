package com.nelumbo.parqueadero.services.registro;

import com.nelumbo.parqueadero.controllers.registro.model.RegistroEntradaRequest;
import com.nelumbo.parqueadero.services.registro.model.Registro;

public interface RegistroService {

    Registro crearRegistroEntrada(RegistroEntradaRequest entradaRequest);

    String crearRegistroSalida(RegistroEntradaRequest entradaRequest);
}
