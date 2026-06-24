/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author emanu
 */
public class Producto extends BaseEntity{
    
    private String nombre;
    private double precio;
    private String descripcion;
    private int stock;
    private String imagen;
    private boolean disponible;
    private Categoria categoria;

    public Producto(String nombre, double precio, String descripcion, int stock, String imagen, boolean disponible,
            Categoria categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.stock = stock;
        this.imagen = imagen;
        this.disponible = disponible;
        setCategoria(categoria);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        if (this.categoria != null) {
            this.categoria.quitarProducto(this);
        }
        this.categoria = categoria;
        if (categoria != null) {
            categoria.agregarProducto(this);
        }
    }

    public void descontarStock(int cantidad) {
        this.stock -= cantidad;
    }

    @Override
    public String toString() {
        String categoriaNombre = categoria == null ? "Sin categoria" : categoria.getNombre();
        return "Producto{id=%d, nombre='%s', precio=%.2f, stock=%d, disponible=%s, categoria='%s'}"
                .formatted(getId(), nombre, precio, stock, disponible ? "SI" : "NO", categoriaNombre);
    }
    
}
