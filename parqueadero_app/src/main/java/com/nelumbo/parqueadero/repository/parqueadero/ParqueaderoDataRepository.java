package com.nelumbo.parqueadero.repository.parqueadero;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;
import java.util.UUID;

public interface ParqueaderoDataRepository extends CrudRepository<ParqueaderoData, UUID>,
        QueryByExampleExecutor<ParqueaderoData> {

    @Query("""
                select pd from ParqueaderoData pd
                inner join RegistroData rd
                on pd.id = rd.parqueaderoId
                where pd.id = ?1
                order by pd.costo
                limit 1
            """)
    ParqueaderoData hasRegistros(UUID id);

    List<ParqueaderoData> findByUsuarioId(UUID usuarioId);
}
