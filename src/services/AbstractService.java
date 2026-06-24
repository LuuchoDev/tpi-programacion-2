/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.BaseEntity;
import exception.EntidadNoEncontradaException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author emanu
 * @param <T>
 */
public abstract class AbstractService <T extends BaseEntity>{
    protected final List<T> registros = new ArrayList<>();
    private long secuencia = 1L;

    protected T guardarNuevo(T entidad) {
        entidad.setId(secuencia++);
        registros.add(entidad);
        return entidad;
    }

    public List<T> listarActivos() {
        return registros.stream()
                .filter(registro -> !registro.isEliminado())
                .toList();
    }

    public Optional<T> buscarActivoPorId(Long id) {
        return registros.stream()
                .filter(registro -> registro.getId().equals(id) && !registro.isEliminado())
                .findFirst();
    }

    public T obtenerActivoPorId(Long id, String nombreEntidad) {
        return buscarActivoPorId(id)
                .orElseThrow(() -> new EntidadNoEncontradaException(nombreEntidad + " no encontrado/a."));
    }

    public void eliminar(Long id, String nombreEntidad) {
        T entidad = obtenerActivoPorId(id, nombreEntidad);
        entidad.setEliminado(true);
    }
}
