package com.nelumbo.parqueadero.repository.vehiculo;

import com.nelumbo.parqueadero.repository.helper.SimpleMapper;
import com.nelumbo.parqueadero.services.vehiculo.model.Vehiculo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VehiculoMapper extends SimpleMapper<Vehiculo, VehiculoData> {
}
