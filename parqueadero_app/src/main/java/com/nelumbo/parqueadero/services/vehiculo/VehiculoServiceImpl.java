package com.nelumbo.parqueadero.services.vehiculo;

import com.nelumbo.parqueadero.services.authentication.AuthenticationService;
import com.nelumbo.parqueadero.services.authentication.model.Usuario;
import com.nelumbo.parqueadero.services.vehiculo.model.*;
import com.nelumbo.parqueadero.services.vehiculo.model.gateway.VehiculoRepository;
import com.nelumbo.parqueadero.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class VehiculoServiceImpl implements VehiculoService{

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Override
    public Vehiculo findByPlacaIfNotExistSave(String placa) {
        Vehiculo vehiculo = vehiculoRepository.findByPlaca(placa);
        if(vehiculo == null){
            vehiculo = vehiculoRepository.save(Vehiculo.builder().placa(placa).build());
        }

        return vehiculo;
    }

    @Override
    public List<VehiculoInside> findAllInside() {
        return vehiculoRepository.findAllInside();
    }

    @Override
    public List<PrimerIndicador> findIndicador1(UUID usuarioId) {
        List<PrimerIndicador> vehiclesMoreRegistred = new ArrayList<>();
        if(isAdmin(usuarioId)){
            vehiclesMoreRegistred = vehiculoRepository.findVehiclesMoreRegistred();
        }else{
            vehiclesMoreRegistred = vehiculoRepository.findVehiclesMoreRegistred(usuarioId);
        }

        return vehiclesMoreRegistred;
    }

    @Override
    public List<SegundoIndicador> findIndicador2(UUID parqueaderoID, UUID usuarioId) {
        List<SegundoIndicador> vehiclesMoreRegistred = new ArrayList<>();
        if(isAdmin(usuarioId)){
            vehiclesMoreRegistred = vehiculoRepository.findVehiclesMoreRegistredByParqueadero(parqueaderoID);
        }else{
            vehiclesMoreRegistred = vehiculoRepository.findVehiclesMoreRegistredByParqueadero(parqueaderoID,usuarioId);
        }

        return vehiclesMoreRegistred;
    }

    @Override
    public List<SegundoIndicador> findVehiclesWithOneRegistred(UUID usuarioId) {
        List<SegundoIndicador> vehiclesMoreRegistred = new ArrayList<>();
        if(isAdmin(usuarioId)){
            vehiclesMoreRegistred = vehiculoRepository.findVehiclesWithOneRegistred();
        }else{
            vehiclesMoreRegistred = vehiculoRepository.findVehiclesWithOneRegistred(usuarioId);
        }

        return vehiclesMoreRegistred;
    }

    @Override
    public Ganancia findGanancia(UUID parqueaderoId) {
        Double hoy = vehiculoRepository.sumaTotalDia(parqueaderoId);
        Double semana = vehiculoRepository.sumaTotalSemana(parqueaderoId);
        Double mes = vehiculoRepository.sumaTotalMes(parqueaderoId);
        Double anno = vehiculoRepository.sumaTotalAnno(parqueaderoId);

        return Ganancia.builder().hoy(hoy).semana(semana).mes(mes).anno(anno).build();
    }

    @Override
    public Vehiculo findVehiculo(UUID usuarioId, String placa) {
        Vehiculo vehiculo = null;
        if(isAdmin(usuarioId)) {
            vehiculo = vehiculoRepository.findRegisterVehiculoByPlaca(placa);
        }else{
            vehiculo = vehiculoRepository.findRegisterVehiculoByPlaca(usuarioId, placa);
        }

        return vehiculo;
    }

    private boolean isAdmin(UUID usuarioId){
        Usuario usuario = authenticationService.findUsuarioById(usuarioId);
        return usuario.getRole().equals(Role.ADMINISTRATOR);
    }
}
