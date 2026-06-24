/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author emanu
 */
public class Categoria extends BaseEntity {
    private String nombre;
    private String descripcion;
    private final List<Producto> productos = new ArrayList<>();

    public Categoria(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Producto> getProductos() {
        return Collections.unmodifiableList(productos);
    }

    public void agregarProducto(Producto producto) {
        if (!productos.contains(producto)) {
            productos.add(producto);
        }
    }

    public void quitarProducto(Producto producto) {
        productos.remove(producto);
    }

    public boolean tieneProductosActivos() {
        return productos.stream().anyMatch(producto -> !producto.isEliminado());
    }

    @Override
    public String toString() {
        return "Categoria{id=%d, nombre='%s', descripcion='%s'}".formatted(getId(), nombre, descripcion);
    }
}
