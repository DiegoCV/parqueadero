package com.nelumbo.parqueadero.services.registro;

import com.nelumbo.parqueadero.controllers.registro.model.RegistroEntradaRequest;
import com.nelumbo.parqueadero.services.common.RegistroException;
import com.nelumbo.parqueadero.services.parqueadero.ParqueaderoService;
import com.nelumbo.parqueadero.services.parqueadero.model.Parqueadero;
import com.nelumbo.parqueadero.services.registro.model.Registro;
import com.nelumbo.parqueadero.services.registro.model.gateway.RegistroRepository;
import com.nelumbo.parqueadero.services.vehiculo.VehiculoService;
import com.nelumbo.parqueadero.services.vehiculo.model.Vehiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.nelumbo.parqueadero.services.common.CommonExceptionMessages.PLACA_EN_USO;
import static com.nelumbo.parqueadero.services.common.CommonExceptionMessages.PLACA_NO_SE_ENCUENTRA_DENTRO;
import static com.nelumbo.parqueadero.services.common.CommonExceptionMessages.SIN_ESPACIOS_DISPONIBLES;
import static com.nelumbo.parqueadero.services.common.CommonMessage.SALIDA_REGISTRADA;

@Service
public class RegistroServiceImpl implements RegistroService{

    @Autowired
    private ParqueaderoService parqueaderoService;

    @Autowired
    private VehiculoService vehiculoService;

    @Autowired
    private RegistroRepository registroRepository;

    @Override
    public Registro crearRegistroEntrada(RegistroEntradaRequest entradaRequest) {
        String placa = entradaRequest.getPlaca();
        UUID parqueaderoId = entradaRequest.getParqueaderoId();
        if(existAnyRegistroActiveWithThatPlaca(placa)){
            throw new RegistroException(PLACA_EN_USO);
        }
        Parqueadero parqueadero = parqueaderoService.findById(parqueaderoId);
        if(!existsAnySpaceAvailable(parqueadero)){
            throw new RegistroException(SIN_ESPACIOS_DISPONIBLES);
        }
        Vehiculo vehiculo = vehiculoService.findByPlacaIfNotExistSave(placa);
        Registro registro = Registro.builder().vehiculoId(vehiculo.getId()).parqueaderoId(parqueaderoId)
                .entry(new Date()).total(0.0).build();

        return registroRepository.save(registro);
    }

    @Override
    public String crearRegistroSalida(RegistroEntradaRequest entradaRequest) {
        String placa = entradaRequest.getPlaca();
        UUID parqueaderoId = entradaRequest.getParqueaderoId();
        Registro registro = registroRepository
                .findLastByPlacaAndParqueaderoId(placa, parqueaderoId);
        if(registro == null || registro.getExit() != null){
            throw new RegistroException(PLACA_NO_SE_ENCUENTRA_DENTRO);
        }
        Date exit = new Date();
        long cantidadDeHoras = calcularDiferenciaEnHoras(registro.getEntry(), exit);
        Parqueadero parqueadero = parqueaderoService.findById(parqueaderoId);
        registro.setExit(exit);
        registro.setTotal(parqueadero.getCosto() * cantidadDeHoras);
        registroRepository.save(registro);

        return SALIDA_REGISTRADA;
    }

    private long calcularDiferenciaEnHoras(Date fecha1, Date fecha2){
            long diferenciaEnMilisegundos = fecha2.getTime() - fecha1.getTime();
            return TimeUnit.MILLISECONDS.toHours(diferenciaEnMilisegundos);
        }
    private boolean existAnyRegistroActiveWithThatPlaca(String placa){
        return registroRepository.findByPlacaAnyRegistroWithOutExitDate(placa) != null;
    }

    private boolean existsAnySpaceAvailable(Parqueadero parqueadero){
        Integer espaciosOcupados = registroRepository.countRegistrosWithoutExitDateByParqueaderoId(parqueadero.getId());
        return parqueadero.getCapacidad() - espaciosOcupados > 0;
    }
}
