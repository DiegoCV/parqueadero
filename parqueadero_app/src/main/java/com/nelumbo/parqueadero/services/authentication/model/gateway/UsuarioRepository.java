package com.nelumbo.parqueadero.services.authentication.model.gateway;

import com.nelumbo.parqueadero.services.authentication.model.Usuario;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository {
    Optional<Usuario> findByEmail(String email);
    Usuario save(Usuario usuario);
    Usuario findById(UUID uuid);
}
