package com.nelumbo.parqueadero.services.common;

public class CommonExceptionMessages {
    public static final String PLACA_EN_USO = "No se puede Registrar Ingreso, ya existe la placa en este u otro parqueadero";
    public static final String SIN_ESPACIOS_DISPONIBLES = "No se puede Registrar Ingreso, ya que no hay espacios disponibles";
    public static final String PLACA_NO_SE_ENCUENTRA_DENTRO = "No se puede Registrar Salida, no existe la placa en el parqueadero";
    public static final String TIENE_REGISTROS = "El parqueadero no se puede borrar porque cuenta con registros";
    public static final String PARQUEADERO_NO_EXISTE = "El parqueadero no existe";
    public static final String VEHICULO_NO_SE_ENCUENTRA_EN_EL_PARQUEADERO = "Vehiculo no se encuentra en el parqueadero";
}
