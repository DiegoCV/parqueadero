package com.nelumbo.parqueadero.repository.registro;

import com.nelumbo.parqueadero.repository.helper.SimpleMapper;
import com.nelumbo.parqueadero.services.registro.model.Registro;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegistroMapper extends SimpleMapper<Registro, RegistroData> {
}
