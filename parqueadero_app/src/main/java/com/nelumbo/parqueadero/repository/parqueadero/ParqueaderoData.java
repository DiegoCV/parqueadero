package com.nelumbo.parqueadero.repository.parqueadero;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "parqueadero")
@Getter
@Setter
public class ParqueaderoData {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "usuario_id", nullable = false)
    private UUID usuarioId;

    @Column(name = "costo", nullable = false)
    private Double costo;

    @Column(name = "capacidad", nullable = false)
    private Integer capacidad;
}
