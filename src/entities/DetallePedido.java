/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author emanu
 */
public class DetallePedido extends BaseEntity{
    private int cantidad;
    private double subtotal;
    private Producto producto;

    public DetallePedido(int cantidad, double subtotal, Producto producto) {
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        String productoNombre = producto == null ? "Sin producto" : producto.getNombre();
        return "Detalle{id=%d, producto='%s', cantidad=%d, subtotal=%.2f}"
                .formatted(getId(), productoNombre, cantidad, subtotal);
    }
}
