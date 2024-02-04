package com.nelumbo.parqueadero.controllers.registro;

import com.nelumbo.parqueadero.controllers.registro.model.RegistroEntradaRequest;
import com.nelumbo.parqueadero.controllers.registro.model.RegistroEntradaResponse;
import com.nelumbo.parqueadero.controllers.registro.model.RegistroSalidaResponse;
import com.nelumbo.parqueadero.services.registro.RegistroService;
import com.nelumbo.parqueadero.services.registro.model.Registro;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/registro", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class RegistroController {

    @Autowired
    private RegistroService registroService;

    @PostMapping("/entrada")
    public ResponseEntity<RegistroEntradaResponse> registrarEntrada(@Valid @RequestBody RegistroEntradaRequest entradaRequest){
        Registro registro = registroService.crearRegistroEntrada(entradaRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(RegistroEntradaResponse.builder().id(registro.getId()).build());
    }

    @PostMapping("/salida")
    public ResponseEntity<RegistroSalidaResponse> registrarSalida(@RequestBody RegistroEntradaRequest entradaRequest){
        String registroSalida = registroService.crearRegistroSalida(entradaRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(RegistroSalidaResponse.builder().mensaje(registroSalida).build());
    }









}
