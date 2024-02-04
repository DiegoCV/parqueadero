package com.nelumbo.parqueadero.repository.parqueadero;

import com.nelumbo.parqueadero.repository.helper.SimpleMapper;
import com.nelumbo.parqueadero.services.parqueadero.model.Parqueadero;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ParqueaderoMapper extends SimpleMapper<Parqueadero, ParqueaderoData> {
}
