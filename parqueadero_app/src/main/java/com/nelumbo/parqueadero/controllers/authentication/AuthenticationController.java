package com.nelumbo.parqueadero.controllers.authentication;

import com.nelumbo.parqueadero.controllers.authentication.model.AuthenticationRequest;
import com.nelumbo.parqueadero.controllers.authentication.model.AuthenticationResponse;
import com.nelumbo.parqueadero.controllers.authentication.model.CreateUserRequest;
import com.nelumbo.parqueadero.controllers.authentication.model.CreateUserResponse;
import com.nelumbo.parqueadero.services.authentication.AuthenticationService;
import com.nelumbo.parqueadero.services.authentication.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody AuthenticationRequest authRequest){
        AuthenticationResponse jwtDto = authenticationService.login(authRequest);
        return ResponseEntity.ok(jwtDto);
    }

    @PostMapping("/create")
    public ResponseEntity<CreateUserResponse> create(@RequestBody CreateUserRequest createUserRequest){
        Usuario usuario = authenticationService.createUser(createUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                CreateUserResponse.builder().id(usuario.getId().toString()).email(usuario.getEmail()).build());
    }
}
