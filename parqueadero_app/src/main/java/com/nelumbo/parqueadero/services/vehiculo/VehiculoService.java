package com.nelumbo.parqueadero.services.vehiculo;

import com.nelumbo.parqueadero.services.vehiculo.model.*;

import java.util.List;
import java.util.UUID;

public interface VehiculoService {
    Vehiculo findByPlacaIfNotExistSave(String placa);
    List<VehiculoInside> findAllInside();
    List<Vehiculo> findVehiculosByParqueadero(UUID parqueaderiId);
    Vehiculo findVehiculoByPlacaInsideParqueadero(String placa, UUID parqueaderoId);
    List<PrimerIndicador> findIndicador1(UUID usuarioId);
    List<SegundoIndicador> findIndicador2(UUID parqueaderiId, UUID usuarioId);
    List<SegundoIndicador> findVehiclesWithOneRegistred(UUID usuarioId);
    Ganancia findGanancia(UUID parqueaderoId);
    Vehiculo findVehiculo(UUID usuarioId, String placa);
}
