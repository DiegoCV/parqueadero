package com.nelumbo.parqueadero.repository.usuario;

import com.nelumbo.parqueadero.repository.helper.AdapterOperations;
import com.nelumbo.parqueadero.services.authentication.model.Usuario;
import com.nelumbo.parqueadero.services.authentication.model.gateway.UsuarioRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class UsuarioRepositoryAdapter extends AdapterOperations<Usuario, UsuarioData, UUID, UsuarioDataRepository>
        implements UsuarioRepository {

    public UsuarioRepositoryAdapter(UsuarioDataRepository repository, UsuarioMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return repository.findByEmail(email).map(usuarioData -> (Usuario) mapper.toEntity(usuarioData));
    }
}
