package com.nelumbo.parqueadero.repository.usuario;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioDataRepository extends CrudRepository<UsuarioData, UUID>,
        QueryByExampleExecutor<UsuarioData> {
    Optional<UsuarioData> findByEmail(String email);
}
