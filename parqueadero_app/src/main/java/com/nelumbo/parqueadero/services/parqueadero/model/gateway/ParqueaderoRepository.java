package com.nelumbo.parqueadero.services.parqueadero.model.gateway;

import com.nelumbo.parqueadero.services.parqueadero.model.Parqueadero;

import java.util.List;
import java.util.UUID;

public interface ParqueaderoRepository {
    Parqueadero save(Parqueadero parqueadero);
    Parqueadero findById(UUID id);
    List<Parqueadero> listAll();
    boolean hasRegistros(UUID id);
    void delete(Parqueadero build);
    List<Parqueadero> listByUsuarioId(UUID id);
}
