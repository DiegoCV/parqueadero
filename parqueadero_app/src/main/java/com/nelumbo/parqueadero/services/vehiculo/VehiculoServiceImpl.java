package com.nelumbo.parqueadero.services.vehiculo;

import com.nelumbo.parqueadero.services.authentication.AuthenticationService;
import com.nelumbo.parqueadero.services.authentication.JwtService;
import com.nelumbo.parqueadero.services.authentication.model.Usuario;
import com.nelumbo.parqueadero.services.common.VehiculoExeption;
import com.nelumbo.parqueadero.services.vehiculo.model.Ganancia;
import com.nelumbo.parqueadero.services.vehiculo.model.PrimerIndicador;
import com.nelumbo.parqueadero.services.vehiculo.model.SegundoIndicador;
import com.nelumbo.parqueadero.services.vehiculo.model.Vehiculo;
import com.nelumbo.parqueadero.services.vehiculo.model.VehiculoInside;
import com.nelumbo.parqueadero.services.vehiculo.model.gateway.VehiculoRepository;
import com.nelumbo.parqueadero.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.nelumbo.parqueadero.services.common.CommonExceptionMessages.VEHICULO_NO_SE_ENCUENTRA_EN_EL_PARQUEADERO;

@Service
public class VehiculoServiceImpl implements VehiculoService{

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private JwtService jwtService;

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
    public List<Vehiculo> findVehiculosByParqueadero(UUID parqueaderoId) {
        UUID usuarioId = jwtService.extractUuid();
        List<Vehiculo> vehiculosByParqueadero = new ArrayList<>();
        if(isAdmin(usuarioId)){
            vehiculosByParqueadero = vehiculoRepository.findVehiculosByParqueadero(parqueaderoId);
        }else{
            vehiculosByParqueadero = vehiculoRepository.findVehiculosByUsuarioAndParqueadero(usuarioId, parqueaderoId);
        }
        return vehiculosByParqueadero;
    }

    @Override
    public Vehiculo findVehiculoByPlacaInsideParqueadero(String placa, UUID parqueaderoId) {
        Vehiculo vehiculo = vehiculoRepository.findVehiculoByPlacaInsideParqueadero(placa, parqueaderoId);
        if(vehiculo == null){
            throw new VehiculoExeption(VEHICULO_NO_SE_ENCUENTRA_EN_EL_PARQUEADERO);
        }
        return vehiculo;
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
