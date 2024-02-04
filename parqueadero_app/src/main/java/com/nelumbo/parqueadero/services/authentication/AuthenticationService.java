package com.nelumbo.parqueadero.services.authentication;

import com.nelumbo.parqueadero.controllers.authentication.model.AuthenticationRequest;
import com.nelumbo.parqueadero.controllers.authentication.model.AuthenticationResponse;
import com.nelumbo.parqueadero.controllers.authentication.model.CreateUserRequest;
import com.nelumbo.parqueadero.services.authentication.model.Usuario;

import java.util.UUID;

public interface AuthenticationService {
    AuthenticationResponse login(AuthenticationRequest authRequest);
    Usuario createUser(CreateUserRequest createUserRequest);
    Usuario findUsuarioById(UUID uuid);
}
