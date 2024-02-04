package com.nelumbo.parqueadero.repository.parqueadero;

import com.nelumbo.parqueadero.repository.helper.AdapterOperations;
import com.nelumbo.parqueadero.services.parqueadero.model.Parqueadero;
import com.nelumbo.parqueadero.services.parqueadero.model.gateway.ParqueaderoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class ParqueaderoRepositoryAdapter extends AdapterOperations<Parqueadero, ParqueaderoData, UUID,
        ParqueaderoDataRepository> implements ParqueaderoRepository {

    public ParqueaderoRepositoryAdapter(ParqueaderoDataRepository repository, ParqueaderoMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public List<Parqueadero> listAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).map(parqueaderoData -> {
            return (Parqueadero)mapper.toEntity(parqueaderoData);
        }).collect(Collectors.toList());
    }

    @Override
    public boolean hasRegistros(UUID id) {
        return repository.hasRegistros(id) != null;
    }

    @Override
    public void delete(Parqueadero build) {
        repository.delete((ParqueaderoData)mapper.toData(build));
    }

    @Override
    public List<Parqueadero> listByUsuarioId(UUID usuarioId) {
        return repository.findByUsuarioId(usuarioId).stream().map(parqueaderoData -> {
            return (Parqueadero)mapper.toEntity(parqueaderoData);
        }).collect(Collectors.toList());
    }
}
