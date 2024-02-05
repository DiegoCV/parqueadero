package com.nelumbo.parqueadero.controllers.vehiculo;

import com.nelumbo.parqueadero.controllers.vehiculo.model.MailRequest;
import com.nelumbo.parqueadero.controllers.vehiculo.model.VehiculoResponse;
import com.nelumbo.parqueadero.services.mail.ConsumidorApiService;
import com.nelumbo.parqueadero.services.mail.model.Mail;
import com.nelumbo.parqueadero.services.vehiculo.VehiculoService;
import com.nelumbo.parqueadero.services.vehiculo.model.Ganancia;
import com.nelumbo.parqueadero.services.vehiculo.model.PrimerIndicador;
import com.nelumbo.parqueadero.services.vehiculo.model.SegundoIndicador;
import com.nelumbo.parqueadero.services.vehiculo.model.Vehiculo;
import com.nelumbo.parqueadero.services.vehiculo.model.VehiculoInside;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/vehiculo", produces = MediaType.APPLICATION_JSON_VALUE)
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @Autowired
    private ConsumidorApiService consumidorApiService;

    @GetMapping("/listarByParqueadero/{parqueaderoId}")
    public ResponseEntity<List<Vehiculo>> listarVehiculosByParqueadero(
            @PathVariable("parqueaderoId") UUID parqueaderoId){
        return ResponseEntity.ok(vehiculoService.findVehiculosByParqueadero(parqueaderoId));
    }

    @PostMapping("/enviarCorreo")
    public String enviarCorreo(@RequestBody MailRequest mailRequest){
        String responseMail = consumidorApiService.enviarMail(Mail.builder().email(mailRequest.getEmail())
                .placa(mailRequest.getPlaca()).mensaje(mailRequest.getMensaje())
                .parqueaderoId(mailRequest.getParqueaderoId()).build());
        return responseMail;
    }

    @GetMapping("/indicador")
    public ResponseEntity<List<PrimerIndicador>> inicador(){
        List<PrimerIndicador> indicador1 = vehiculoService.findIndicador1();
        return ResponseEntity.ok(indicador1);
    }

    @GetMapping("/indicadorDos/{parqueaderoId}")
    public ResponseEntity<List<SegundoIndicador>> inicadorDos(@PathVariable("parqueaderoId") UUID parqueaderoId){
        List<SegundoIndicador> indicador2 = vehiculoService.findIndicador2(parqueaderoId);
        return ResponseEntity.ok(indicador2);
    }

    @GetMapping("/indicadorTres")
    public ResponseEntity<List<SegundoIndicador>> inicadorTres(){
        List<SegundoIndicador> vehiclesWithOneRegistred = vehiculoService.findVehiclesWithOneRegistred();
        return ResponseEntity.ok(vehiclesWithOneRegistred);
    }

    @GetMapping("/indicadorCuatro/{parqueaderoId}")
    public ResponseEntity<Ganancia> inicadorCuatro(@PathVariable("parqueaderoId") UUID parqueaderoId){
        Ganancia ganancia = vehiculoService.findGanancia(parqueaderoId);
        return ResponseEntity.ok(ganancia);
    }

    @GetMapping("/indicadorCinco/placa/{placa}")
    public ResponseEntity<Vehiculo> inicadorCinco(@PathVariable("placa") String placa){
        Vehiculo vehiculo = vehiculoService.findVehiculo(placa);
        return ResponseEntity.ok(vehiculo);
    }

    private VehiculoResponse toVehiculoResponse(VehiculoInside vehiculoInside){
        return VehiculoResponse.builder().id(vehiculoInside.getId()).placa(vehiculoInside.getPlaca())
                .fechaIngreso(vehiculoInside.getFechaIngreso()).nombreParqueadero(vehiculoInside.getNombreParqueadero())
                .build();
    }
}
