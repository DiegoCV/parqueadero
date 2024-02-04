package com.nelumbo.parqueadero.repository.registro;

import com.nelumbo.parqueadero.repository.helper.AdapterOperations;
import com.nelumbo.parqueadero.services.registro.model.Registro;
import com.nelumbo.parqueadero.services.registro.model.gateway.RegistroRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class RegistroRepositoryAdapter extends AdapterOperations<Registro, RegistroData, UUID,
        RegistroDataRepository> implements RegistroRepository {

    public RegistroRepositoryAdapter(RegistroDataRepository repository, RegistroMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public Registro findByPlacaAnyRegistroWithOutExitDate(String placa) {
        return (Registro) mapper.toEntity(repository.findByPlacaAnyRegistroWithOutExitDate(placa));
    }

    @Override
    public Integer countRegistrosWithoutExitDateByParqueaderoId(UUID parqueaderoId) {
        return repository.countRegistrosWithoutExitDateByParqueaderoId(parqueaderoId);
    }

    @Override
    public Registro findLastByPlacaAndParqueaderoId(String placa, UUID parqueaderoID) {
        return (Registro) mapper.toEntity(repository.findLastByPlacaAndParqueaderoId(placa, parqueaderoID));
    }
}
