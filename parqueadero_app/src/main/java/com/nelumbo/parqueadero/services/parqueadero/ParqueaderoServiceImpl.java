package com.nelumbo.parqueadero.services.parqueadero;

import com.nelumbo.parqueadero.services.common.ParqueaderoException;
import com.nelumbo.parqueadero.services.parqueadero.model.Parqueadero;
import com.nelumbo.parqueadero.services.parqueadero.model.gateway.ParqueaderoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.nelumbo.parqueadero.services.common.CommonExceptionMessages.PARQUEADERO_NO_EXISTE;
import static com.nelumbo.parqueadero.services.common.CommonExceptionMessages.TIENE_REGISTROS;

@Service
public class ParqueaderoServiceImpl implements ParqueaderoService {

    @Autowired
    private ParqueaderoRepository parqueaderoRepository;

    @Override
    public Parqueadero save(Parqueadero parqueadero) {
        return parqueaderoRepository.save(parqueadero);
    }

    @Override
    public Parqueadero findById(UUID id) {
        Parqueadero parqueadero = parqueaderoRepository.findById(id);
        if(parqueadero == null){
            throw new ParqueaderoException(PARQUEADERO_NO_EXISTE);
        }

        return parqueadero;
    }

    @Override
    public List<Parqueadero> listAll() {
        return parqueaderoRepository.listAll();
    }

    @Override
    public void delete(UUID id) {
        if(parqueaderoRepository.hasRegistros(id)){
            throw new ParqueaderoException(TIENE_REGISTROS);
        }
        parqueaderoRepository.delete(Parqueadero.builder().id(id).build());
    }

    @Override
    public List<Parqueadero> listByUsuario(UUID usuarioId) {
        return parqueaderoRepository.listByUsuarioId(usuarioId);
    }
}
