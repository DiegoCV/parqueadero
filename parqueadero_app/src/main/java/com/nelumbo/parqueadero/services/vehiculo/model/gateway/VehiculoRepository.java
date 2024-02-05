package com.nelumbo.parqueadero.services.vehiculo.model.gateway;

import com.nelumbo.parqueadero.services.vehiculo.model.PrimerIndicador;
import com.nelumbo.parqueadero.services.vehiculo.model.SegundoIndicador;
import com.nelumbo.parqueadero.services.vehiculo.model.Vehiculo;
import com.nelumbo.parqueadero.services.vehiculo.model.VehiculoInside;

import java.util.List;
import java.util.UUID;

public interface VehiculoRepository {
    Vehiculo save(Vehiculo vehiculo);
    Vehiculo findByPlaca(String placa);
    List<VehiculoInside> findAllInside();
    List<Vehiculo> findVehiculosByParqueadero(UUID parqueaderoId);

    List<PrimerIndicador> findVehiclesMoreRegistred();
    List<PrimerIndicador> findVehiclesMoreRegistred(UUID usuarioId);

    List<SegundoIndicador> findVehiclesMoreRegistredByParqueadero(UUID parqueaderoId);
    List<SegundoIndicador> findVehiclesMoreRegistredByParqueadero(UUID parqueaderoId, UUID usuarioId);

    List<SegundoIndicador> findVehiclesWithOneRegistred();
    List<SegundoIndicador> findVehiclesWithOneRegistred(UUID usuarioId);

    Double sumaTotalDia(UUID parqueaderoId);
    Double sumaTotalSemana(UUID parqueaderoId);
    Double sumaTotalMes(UUID parqueaderoId);
    Double sumaTotalAnno(UUID parqueaderoId);

    Vehiculo findRegisterVehiculoByPlaca(String placa);
    Vehiculo findRegisterVehiculoByPlaca(UUID usuarioId, String placa);

    List<Vehiculo> findVehiculosByUsuarioAndParqueadero(UUID usuarioId, UUID parqueaderoId);
}
