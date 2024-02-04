package com.nelumbo.parqueadero.services.registro.model.gateway;

import com.nelumbo.parqueadero.services.registro.model.Registro;

import java.util.UUID;

public interface RegistroRepository {

    Registro save(Registro registro);
    Registro findByPlacaAnyRegistroWithOutExitDate(String placa);
    Integer countRegistrosWithoutExitDateByParqueaderoId(UUID parqueaderoId);
    Registro findLastByPlacaAndParqueaderoId(String placa, UUID parqueaderoID);
}
