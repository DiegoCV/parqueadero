package com.nelumbo.parqueadero.repository.registro;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.UUID;

public interface RegistroDataRepository extends CrudRepository<RegistroData, UUID>,
        QueryByExampleExecutor<RegistroData> {

    @Query("""
                SELECT rd FROM RegistroData rd
                INNER JOIN VehiculoData vd 
                ON rd.vehiculoId = vd.id
                WHERE vd.placa like %:placa% AND rd.exit IS NULL
            """)
    RegistroData findByPlacaAnyRegistroWithOutExitDate(String placa);

    @Query("""
                SELECT COUNT(*) FROM RegistroData rd
                WHERE rd.parqueaderoId = ?1 AND rd.exit IS NULL
            """)
    Integer countRegistrosWithoutExitDateByParqueaderoId(UUID parqueaderoId);

    @Query("""
                SELECT rd FROM RegistroData rd
                INNER JOIN VehiculoData vd 
                ON rd.vehiculoId = vd.id
                WHERE vd.placa like %?1% AND rd.parqueaderoId = ?2
                ORDER BY rd.entry DESC LIMIT 1
            """)
    RegistroData findLastByPlacaAndParqueaderoId(String placa, UUID parqueaderoID);
}