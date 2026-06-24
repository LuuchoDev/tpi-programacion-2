/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.Categoria;
import exception.DuplicadoException;
import exception.ValorInvalidoException;

/**
 *
 * @author emanu
 */
public class CategoriaService extends AbstractService<Categoria>{
   
    public Categoria crear(String nombre, String descripcion) {
        validarTexto(nombre, "El nombre de la categoria es obligatorio.");
        validarNombreUnico(nombre, null);
        return guardarNuevo(new Categoria(nombre.trim(), normalizar(descripcion)));
    }

    public Categoria editar(Long id, String nombre, String descripcion) {
        Categoria categoria = obtenerActivoPorId(id, "Categoria");
        if (!estaVacio(nombre)) {
            validarNombreUnico(nombre, id);
            categoria.setNombre(nombre.trim());
        }
        if (!estaVacio(descripcion)) {
            categoria.setDescripcion(descripcion.trim());
        }
        return categoria;
    }

    @Override
    public void eliminar(Long id, String nombreEntidad) {
        Categoria categoria = obtenerActivoPorId(id, nombreEntidad);
        if (categoria.tieneProductosActivos()) {
            throw new ValorInvalidoException("No se puede eliminar una categoria con productos activos asociados.");
        }
        categoria.setEliminado(true);
    }

    private void validarNombreUnico(String nombre, Long idActual) {
        boolean existe = registros.stream()
                .filter(categoria -> !categoria.isEliminado())
                .anyMatch(categoria -> categoria.getNombre().equalsIgnoreCase(nombre.trim())
                        && !categoria.getId().equals(idActual));
        if (existe) {
            throw new DuplicadoException("Ya existe una categoria con ese nombre.");
        }
    }

    static void validarTexto(String texto, String mensaje) {
        if (estaVacio(texto)) {
            throw new ValorInvalidoException(mensaje);
        }
    }

    static boolean estaVacio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }

    static String normalizar(String texto) {
        return texto == null ? "" : texto.trim();
    }
}
