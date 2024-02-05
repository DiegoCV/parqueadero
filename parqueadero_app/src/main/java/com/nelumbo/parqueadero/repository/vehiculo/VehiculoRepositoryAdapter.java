package com.nelumbo.parqueadero.repository.vehiculo;

import com.nelumbo.parqueadero.repository.helper.AdapterOperations;
import com.nelumbo.parqueadero.services.vehiculo.model.PrimerIndicador;
import com.nelumbo.parqueadero.services.vehiculo.model.SegundoIndicador;
import com.nelumbo.parqueadero.services.vehiculo.model.Vehiculo;
import com.nelumbo.parqueadero.services.vehiculo.model.VehiculoInside;
import com.nelumbo.parqueadero.services.vehiculo.model.gateway.VehiculoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class VehiculoRepositoryAdapter extends AdapterOperations<Vehiculo, VehiculoData, UUID, VehiculoDataRepository>
        implements VehiculoRepository {

    public VehiculoRepositoryAdapter(VehiculoDataRepository repository, VehiculoMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public Vehiculo findByPlaca(String placa) {
        return (Vehiculo) mapper.toEntity(repository.findByPlaca(placa));
    }

    @Override
    public List<VehiculoInside> findAllInside() {
        return repository.findAllInside();
    }

    @Override
    public List<Vehiculo> findVehiculosByParqueadero(UUID parqueaderoId) {
        return repository.findByParquederoId(parqueaderoId).stream()
                .map(vehiculoData -> (Vehiculo)mapper.toEntity(vehiculoData)).collect(Collectors.toList());
    }

    @Override
    public List<PrimerIndicador> findVehiclesMoreRegistred() {
        return repository.findVehiclesMoreRegistred();
    }

    @Override
    public List<PrimerIndicador> findVehiclesMoreRegistred(UUID usuarioId) {
        return repository.findVehiclesMoreRegistred(usuarioId);
    }

    @Override
    public List<SegundoIndicador> findVehiclesMoreRegistredByParqueadero(UUID parqueaderoId) {
        return repository.findVehiclesMoreRegistredByParqueadero(parqueaderoId);
    }

    @Override
    public List<SegundoIndicador> findVehiclesMoreRegistredByParqueadero(UUID parqueaderoId, UUID usuarioId) {
        return repository.findVehiclesMoreRegistredByParqueadero(parqueaderoId, usuarioId);
    }

    @Override
    public List<SegundoIndicador> findVehiclesWithOneRegistred() {
        return repository.findVehiclesWithOneRegistred();
    }

    @Override
    public List<SegundoIndicador> findVehiclesWithOneRegistred(UUID usuarioId) {
        return repository.findVehiclesWithOneRegistred(usuarioId);
    }

    @Override
    public Double sumaTotalDia(UUID parqueaderoId) {
        return repository.sumaTotalDia(parqueaderoId);
    }

    @Override
    public Double sumaTotalSemana(UUID parqueaderoId) {
        return repository.sumaTotalSemana(parqueaderoId);
    }

    @Override
    public Double sumaTotalMes(UUID parqueaderoId) {
        return repository.sumaTotalMes(parqueaderoId);
    }

    @Override
    public Double sumaTotalAnno(UUID parqueaderoId) {
        return repository.sumaTotalAnno(parqueaderoId);
    }

    @Override
    public Vehiculo findRegisterVehiculoByPlaca(String placa) {
        return (Vehiculo)mapper.toEntity(repository.findRegisterVehiculoByPlaca(placa));
    }

    @Override
    public Vehiculo findRegisterVehiculoByPlaca(UUID usuarioId, String placa) {
        return (Vehiculo)mapper.toEntity(repository.findRegisterVehiculoByPlaca(usuarioId, placa));
    }

    @Override
    public List<Vehiculo> findVehiculosByUsuarioAndParqueadero(UUID usuarioId, UUID parqueaderoId) {
        return repository.findByUsuarioIdAndParquederoId(usuarioId, parqueaderoId).stream()
                .map(vehiculoData -> (Vehiculo)mapper.toEntity(vehiculoData)).collect(Collectors.toList());
    }

    @Override
    public Vehiculo findVehiculoByPlacaInsideParqueadero(String placa, UUID parqueaderoId) {
        return (Vehiculo) mapper.toEntity(repository.findVehiculoByPlacaInsideParqueadero(placa, parqueaderoId));
    }


}
