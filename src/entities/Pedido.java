/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import enums.Estado;
import enums.FormaPago;
import exception.StockInsuficienteException;
import exception.ValorInvalidoException;
import interfaces.Calculable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author emanu
 */
public class Pedido extends BaseEntity implements Calculable{
    private LocalDate fecha;
    private Estado estado;
    private double total;
    private FormaPago formaPago;
    private Usuario usuario;
    private final List<DetallePedido> detalles = new ArrayList<>();

    public Pedido(Usuario usuario, FormaPago formaPago) {
        this.fecha = LocalDate.now();
        this.estado = Estado.PENDIENTE;
        this.formaPago = formaPago;
        this.usuario = usuario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public double getTotal() {
        return total;
    }

    public FormaPago getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(FormaPago formaPago) {
        this.formaPago = formaPago;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public List<DetallePedido> getDetalles() {
        return Collections.unmodifiableList(detalles);
    }

    public DetallePedido addDetallePedido(int cantidad, Double precioUnitario, Producto producto) {
        if (cantidad <= 0) {
            throw new ValorInvalidoException("La cantidad del detalle debe ser mayor a cero.");
        }
        if (producto == null || producto.isEliminado() || !producto.isDisponible()) {
            throw new ValorInvalidoException("El producto seleccionado no esta disponible.");
        }
        if (producto.getStock() < cantidad) {
            throw new StockInsuficienteException("Stock insuficiente para el producto " + producto.getNombre() + ".");
        }
        DetallePedido detalle = new DetallePedido(cantidad, cantidad * precioUnitario, producto);
        detalles.add(detalle);
        producto.descontarStock(cantidad);
        calcularTotal();
        return detalle;
    }

    public Optional<DetallePedido> findeDetallePedidoByProducto(Producto producto) {
        return detalles.stream()
                .filter(detalle -> detalle.getProducto().equals(producto) && !detalle.isEliminado())
                .findFirst();
    }

    public Optional<DetallePedido> findDetallePedidoByProducto(Producto producto) {
        return findeDetallePedidoByProducto(producto);
    }

    public boolean deleteDetallePedidoByProducto(Producto producto) {
        Optional<DetallePedido> detalle = findeDetallePedidoByProducto(producto);
        detalle.ifPresent(item -> item.setEliminado(true));
        calcularTotal();
        return detalle.isPresent();
    }

    @Override
    public double calcularTotal() {
        total = detalles.stream()
                .filter(detalle -> !detalle.isEliminado())
                .mapToDouble(DetallePedido::getSubtotal)
                .sum();
        return total;
    }

    @Override
    public String toString() {
        String nombreUsuario = usuario == null ? "Sin usuario" : usuario.getNombre() + " " + usuario.getApellido();
        return "Pedido{id=%d, usuario='%s', estado=%s, formaPago=%s, total=%.2f, fecha=%s, detalles=%d}"
                .formatted(getId(), nombreUsuario, estado, formaPago, total, fecha, detalles.size());
    }
}
