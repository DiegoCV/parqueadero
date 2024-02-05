package com.nelumbo.parqueadero.repository.vehiculo;

import com.nelumbo.parqueadero.services.vehiculo.model.PrimerIndicador;
import com.nelumbo.parqueadero.services.vehiculo.model.SegundoIndicador;
import com.nelumbo.parqueadero.services.vehiculo.model.Vehiculo;
import com.nelumbo.parqueadero.services.vehiculo.model.VehiculoInside;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;
import java.util.UUID;

public interface VehiculoDataRepository extends CrudRepository<VehiculoData, UUID>,
        QueryByExampleExecutor<VehiculoData> {

    VehiculoData findByPlaca(String placa);

    @Query("""
            SELECT DISTINCT vd FROM VehiculoData vd
            INNER JOIN RegistroData rd
            ON vd.id = rd.vehiculoId
            WHERE rd.parqueaderoId = ?1
            """)
    List<VehiculoData> findByParquederoId(UUID parqueaderoId);

    @Query("""
            SELECT DISTINCT vd FROM VehiculoData vd
            INNER JOIN RegistroData rd
            ON vd.id = rd.vehiculoId
            INNER JOIN ParqueaderoData pd
            ON rd.parqueaderoId = pd.id
            WHERE pd.usuarioId = ?1 AND rd.parqueaderoId = ?2
            """)
    List<VehiculoData> findByUsuarioIdAndParquederoId(UUID usuarioId, UUID parqueaderoId);

    @Query("""
                SELECT new com.nelumbo.parqueadero.services.vehiculo.model.VehiculoInside(
                    vd.id, vd.placa, rd.entry, p.nombre) 
                FROM VehiculoData vd
                INNER JOIN RegistroData rd
                ON vd.id = rd.vehiculoId
                INNER JOIN ParqueaderoData p
                ON rd.parqueaderoId = p.id
                WHERE rd.exit IS NULL
            """)
    List<VehiculoInside> findAllInside();

    @Query("""
                select new com.nelumbo.parqueadero.services.vehiculo.model.PrimerIndicador(
                    v.placa, count(v.id))
                from VehiculoData v
                inner join RegistroData vp
                on v.id = vp.vehiculoId
                group by v.placa
                order by count(v.id) desc
                limit 10
            """)
    List<PrimerIndicador> findVehiclesMoreRegistred();

    @Query("""
                select new com.nelumbo.parqueadero.services.vehiculo.model.PrimerIndicador(
                    v.placa, count(v.id))
                from VehiculoData v
                inner join RegistroData vp
                on v.id = vp.vehiculoId
                inner join ParqueaderoData p
                on vp.parqueaderoId = p.id
                where p.usuarioId = ?1
                group by v.placa
                order by count(v.id) desc
                limit 10
            """)
    List<PrimerIndicador> findVehiclesMoreRegistred(UUID usuarioId);

    @Query("""
                select new com.nelumbo.parqueadero.services.vehiculo.model.SegundoIndicador(
                    p.nombre, v.placa, count(v.id)) 
                from VehiculoData v
                inner join RegistroData vp
                on v.id = vp.vehiculoId
                inner join ParqueaderoData p
                on vp.parqueaderoId = p.id
                where vp.parqueaderoId = ?1
                group by p.nombre, v.placa
                order by count(v.id) desc
                limit 10
            """)
    List<SegundoIndicador> findVehiclesMoreRegistredByParqueadero(UUID parqueaderoId);

    @Query("""
                select new com.nelumbo.parqueadero.services.vehiculo.model.SegundoIndicador(
                    p.nombre, v.placa, count(v.id)) 
                from VehiculoData v
                inner join RegistroData vp
                on v.id = vp.vehiculoId
                inner join ParqueaderoData p
                on vp.parqueaderoId = p.id
                where vp.parqueaderoId = ?1
                and p.usuarioId = ?2
                group by p.nombre, v.placa
                order by count(v.id) desc
                limit 10
            """)
    List<SegundoIndicador> findVehiclesMoreRegistredByParqueadero(UUID parqueaderoId, UUID usuarioId);

//tercero
    @Query("""
            select new com.nelumbo.parqueadero.services.vehiculo.model.SegundoIndicador(
                p.nombre, v.placa, count(v.id)) from VehiculoData v
            inner join RegistroData vp
            on v.id = vp.vehiculoId
            inner join ParqueaderoData p\s
            on vp.parqueaderoId = p.id
            group by p.nombre, v.placa
            having count(v.id) = 1
            """)
    List<SegundoIndicador> findVehiclesWithOneRegistred();

    @Query("""
            select new com.nelumbo.parqueadero.services.vehiculo.model.SegundoIndicador(
                p.nombre, v.placa, count(v.id)) from VehiculoData v
            inner join RegistroData vp
            on v.id = vp.vehiculoId
            inner join ParqueaderoData p\s
            on vp.parqueaderoId = p.id
            where p.usuarioId = ?1
            group by p.nombre, v.placa
            having count(v.id) = 1
            """)
    List<SegundoIndicador> findVehiclesWithOneRegistred(UUID usuarioId);

    @Query("""
            select sum(vp.total) from RegistroData vp
            where vp.parqueaderoId = ?1
            and Date(vp.exit) = CURRENT_DATE
            """)
    Double sumaTotalDia(UUID parqueaderoId);

    @Query("""
            select sum(vp.total) from RegistroData vp
            where vp.parqueaderoId = ?1          
            and EXTRACT(WEEK FROM vp.exit) = EXTRACT(WEEK FROM CURRENT_DATE)
              AND EXTRACT(YEAR FROM vp.exit) = EXTRACT(YEAR FROM CURRENT_DATE)
            """)
    Double sumaTotalSemana(UUID parqueaderoId);

    @Query("""
            select sum(vp.total) from RegistroData vp
            where vp.parqueaderoId = ?1          
            and EXTRACT(MONTH FROM vp.exit) = EXTRACT(MONTH FROM CURRENT_DATE)
            AND EXTRACT(YEAR FROM vp.exit) = EXTRACT(YEAR FROM CURRENT_DATE)
            """)
    Double sumaTotalMes(UUID parqueaderoId);

    @Query("""
            select sum(vp.total) from RegistroData vp
            where vp.parqueaderoId = ?1                     
            AND EXTRACT(YEAR FROM vp.exit) = EXTRACT(YEAR FROM CURRENT_DATE)
            """)
    Double sumaTotalAnno(UUID parqueaderoId);

    @Query("""
             select v from VehiculoData v
             inner join RegistroData vp
             on v.id = vp.vehiculoId
             where vp.exit is null
             and v.placa like %:placa%
            """)
    VehiculoData findRegisterVehiculoByPlaca(String placa);

    @Query("""
            select v from VehiculoData v
             inner join RegistroData vp
             on v.id = vp.vehiculoId
             inner join ParqueaderoData p
             on vp.parqueaderoId = p.id
             where p.usuarioId = ?1 
             and vp.exit is null
             and v.placa like %?2%
            """)
    VehiculoData findRegisterVehiculoByPlaca(UUID usuarioId, String placa);
}
