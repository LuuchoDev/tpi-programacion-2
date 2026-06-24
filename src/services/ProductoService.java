/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.Categoria;
import entities.Producto;
import exception.ValorInvalidoException;

/**
 *
 * @author emanu
 */
public class ProductoService extends AbstractService<Producto>{
    
    private final CategoriaService categoriaService;

    public ProductoService(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    public Producto crear(String nombre, String descripcion, double precio, int stock, String imagen,
            boolean disponible, Long categoriaId) {
        validarProducto(nombre, precio, stock);
        Categoria categoria = categoriaService.obtenerActivoPorId(categoriaId, "Categoria");
        Producto producto = new Producto(nombre.trim(), precio, CategoriaService.normalizar(descripcion), stock,
                CategoriaService.normalizar(imagen), disponible, categoria);
        return guardarNuevo(producto);
    }

    public Producto editar(Long id, String nombre, String descripcion, Double precio, Integer stock, String imagen,
            Boolean disponible, Long categoriaId) {
        Producto producto = obtenerActivoPorId(id, "Producto");
        if (!CategoriaService.estaVacio(nombre)) {
            producto.setNombre(nombre.trim());
        }
        if (!CategoriaService.estaVacio(descripcion)) {
            producto.setDescripcion(descripcion.trim());
        }
        if (precio != null) {
            validarPrecio(precio);
            producto.setPrecio(precio);
        }
        if (stock != null) {
            validarStock(stock);
            producto.setStock(stock);
        }
        if (!CategoriaService.estaVacio(imagen)) {
            producto.setImagen(imagen.trim());
        }
        if (disponible != null) {
            producto.setDisponible(disponible);
        }
        if (categoriaId != null) {
            producto.setCategoria(categoriaService.obtenerActivoPorId(categoriaId, "Categoria"));
        }
        return producto;
    }

    private void validarProducto(String nombre, double precio, int stock) {
        CategoriaService.validarTexto(nombre, "El nombre del producto es obligatorio.");
        validarPrecio(precio);
        validarStock(stock);
    }

    private void validarPrecio(double precio) {
        if (precio < 0) {
            throw new ValorInvalidoException("El precio no puede ser negativo.");
        }
    }

    private void validarStock(int stock) {
        if (stock < 0) {
            throw new ValorInvalidoException("El stock no puede ser negativo.");
        }
    }
    
}
