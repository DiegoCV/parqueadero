package com.nelumbo.parqueadero.repository.helper;

public interface SimpleMapper<E, D> {
    D toData(E entity);
    E toEntity(D data);
}
