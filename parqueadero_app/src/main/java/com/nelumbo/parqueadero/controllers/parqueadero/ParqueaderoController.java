package com.nelumbo.parqueadero.controllers.parqueadero;

import com.nelumbo.parqueadero.controllers.parqueadero.model.ParqueaderoRequest;
import com.nelumbo.parqueadero.controllers.parqueadero.model.ParquederoResponse;
import com.nelumbo.parqueadero.services.parqueadero.ParqueaderoService;
import com.nelumbo.parqueadero.services.parqueadero.model.Parqueadero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/parqueadero", produces = MediaType.APPLICATION_JSON_VALUE)
public class ParqueaderoController {

    @Autowired
    private ParqueaderoService parqueaderoService;

    @PostMapping("/create")
    public ResponseEntity<ParquederoResponse> create(@RequestBody ParqueaderoRequest parqueaderoRequest){
        Parqueadero parqueadero = Parqueadero.builder().nombre(parqueaderoRequest.getNombre())
                .usuarioId(UUID.fromString(parqueaderoRequest.getUsuario())).costo(parqueaderoRequest.getCosto())
                .capacidad(parqueaderoRequest.getCapacidad()).build();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(toParqueaderoResponse(parqueaderoService.save(parqueadero)));
    }

    @PutMapping("/update")
    public ResponseEntity<ParquederoResponse> update(@RequestBody ParqueaderoRequest parqueaderoRequest){
        Parqueadero parqueadero = Parqueadero.builder().id(UUID.fromString(parqueaderoRequest.getId()))
                .nombre(parqueaderoRequest.getNombre()).usuarioId(UUID.fromString(parqueaderoRequest.getUsuario()))
                .costo(parqueaderoRequest.getCosto()).capacidad(parqueaderoRequest.getCapacidad()).build();
        return ResponseEntity.status(HttpStatus.OK)
                .body(toParqueaderoResponse(parqueaderoService.save(parqueadero)));
    }

    @DeleteMapping("/delete/{parqueaderoId}")
    public ResponseEntity delete(@PathVariable("parqueaderoId") UUID parqueaderoId){
        parqueaderoService.delete(parqueaderoId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{parqueaderoId}")
    public ResponseEntity<ParquederoResponse> read(@PathVariable("parqueaderoId") UUID parqueaderoId){

        return ResponseEntity.status(HttpStatus.OK)
                .body(toParqueaderoResponse(parqueaderoService.findById(parqueaderoId)));
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<ParquederoResponse>> listAll(){
        return ResponseEntity.status(HttpStatus.OK).body(
                parqueaderoService.listAll().stream().map(parqueadero -> {
                    return toParqueaderoResponse(parqueadero);
                }).collect(Collectors.toList())
        );
    }

    @GetMapping("/list")
    public ResponseEntity<List<ParquederoResponse>> listByUsuario(@RequestBody ParqueaderoRequest parqueaderoRequest){
        return ResponseEntity.status(HttpStatus.OK).body(
                parqueaderoService.listByUsuario(UUID.fromString(parqueaderoRequest.getUsuario())).stream()
                        .map(parqueadero -> { return toParqueaderoResponse(parqueadero);})
                        .collect(Collectors.toList())
        );
    }

    private ParquederoResponse toParqueaderoResponse(Parqueadero parqueadero){
        return ParquederoResponse.builder().id(parqueadero.getId().toString()).nombre(parqueadero.getNombre())
                .costo(parqueadero.getCosto()).capacidad(parqueadero.getCapacidad()).build();
    }


}
