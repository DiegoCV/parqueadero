package com.nelumbo.parqueadero.services.parqueadero;

import com.nelumbo.parqueadero.services.parqueadero.model.Parqueadero;

import java.util.List;
import java.util.UUID;

public interface ParqueaderoService {
    Parqueadero save(Parqueadero parqueadero);
    Parqueadero findById(UUID id);
    List<Parqueadero> listAll();
    void delete(UUID id);
    List<Parqueadero> listByUsuario(UUID fromString);
}
