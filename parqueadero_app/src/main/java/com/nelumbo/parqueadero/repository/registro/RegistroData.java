package com.nelumbo.parqueadero.repository.registro;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "vehiculos_parqueados")
@Getter
@Setter
public class RegistroData {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "vehiculo_id", nullable = false)
    private UUID vehiculoId;

    @Column(name = "parqueadero_id", nullable = false)
    private UUID parqueaderoId;

    @Column(name = "entry_date", nullable = false)
    private Date entry;

    @Column(name = "exit_date")
    private Date exit;

    private Double total;
}
