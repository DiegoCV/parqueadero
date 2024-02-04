package com.nelumbo.parqueadero.controllers.vehiculo;

import com.nelumbo.parqueadero.controllers.vehiculo.model.*;
import com.nelumbo.parqueadero.services.mail.ConsumidorApiService;
import com.nelumbo.parqueadero.services.mail.model.Mail;
import com.nelumbo.parqueadero.services.vehiculo.VehiculoService;
import com.nelumbo.parqueadero.services.vehiculo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/vehiculo", produces = MediaType.APPLICATION_JSON_VALUE)
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @Autowired
    private ConsumidorApiService consumidorApiService;

    @GetMapping("/listarParqueados")
    public ResponseEntity<List<VehiculoResponse>> listarParqueados(){
        return ResponseEntity.ok(vehiculoService.findAllInside().stream()
                .map(this::toVehiculoResponse).collect(Collectors.toList()));
    }

    @PostMapping("/enviarCorreo")
    public String enviarCorreo(@RequestBody MailRequest mailRequest){
        String responseMail = consumidorApiService.enviarMail(Mail.builder().email(mailRequest.getEmail())
                .placa(mailRequest.getPlaca()).mensaje(mailRequest.getMensaje())
                .parqueaderoNombre(mailRequest.getParqueaderoNombre()).build());
        return responseMail;
    }

    @PostMapping("/indicador")
    public ResponseEntity<List<PrimerIndicador>> inicador(@RequestBody Informe informe){
        List<PrimerIndicador> indicador1 = vehiculoService
                .findIndicador1(UUID.fromString(informe.getUsuario()));
        return ResponseEntity.ok(indicador1);
    }

    @PostMapping("/indicadorDos")
    public ResponseEntity<List<SegundoIndicador>> inicadorDos(@RequestBody InformeDos informe){
        List<SegundoIndicador> indicador2 = vehiculoService.findIndicador2(UUID.fromString(informe.getParqueadero()),
                UUID.fromString(informe.getUsuario()));
        return ResponseEntity.ok(indicador2);
    }

    @PostMapping("/indicadorTres")
    public ResponseEntity<List<SegundoIndicador>> inicadorTres(@RequestBody Informe informe){
        List<SegundoIndicador> vehiclesWithOneRegistred = vehiculoService
                .findVehiclesWithOneRegistred(UUID.fromString(informe.getUsuario()));
        return ResponseEntity.ok(vehiclesWithOneRegistred);
    }

    @PostMapping("/indicadorCuatro")
    public ResponseEntity<Ganancia> inicadorCuatro(@RequestBody InformeDos informe){
        Ganancia ganancia = vehiculoService.findGanancia(UUID.fromString(informe.getParqueadero()));
        return ResponseEntity.ok(ganancia);
    }

    @PostMapping("/indicadorCinco")
    public ResponseEntity<Vehiculo> inicadorCinco(@RequestBody InformeCinco informe){
        Vehiculo vehiculo = vehiculoService.findVehiculo(UUID.fromString(informe.getUsuario()),
                informe.getPlaca());
        return ResponseEntity.ok(vehiculo);
    }

    private VehiculoResponse toVehiculoResponse(VehiculoInside vehiculoInside){
        return VehiculoResponse.builder().id(vehiculoInside.getId()).placa(vehiculoInside.getPlaca())
                .fechaIngreso(vehiculoInside.getFechaIngreso()).nombreParqueadero(vehiculoInside.getNombreParqueadero())
                .build();
    }
}
