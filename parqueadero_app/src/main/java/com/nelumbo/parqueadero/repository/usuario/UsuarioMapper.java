package com.nelumbo.parqueadero.repository.usuario;

import com.nelumbo.parqueadero.repository.helper.SimpleMapper;
import com.nelumbo.parqueadero.services.authentication.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper extends SimpleMapper<Usuario, UsuarioData> {
}
