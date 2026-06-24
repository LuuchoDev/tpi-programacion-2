/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.DetallePedido;
import entities.Pedido;
import entities.Producto;
import entities.Usuario;
import enums.Estado;
import enums.FormaPago;
import exception.ValorInvalidoException;

/**
 *
 * @author emanu
 */
public class PedidoService extends AbstractService<Pedido>{
    private final UsuarioService usuarioService;
    private final ProductoService productoService;
    private long detalleSecuencia = 1L;

    public PedidoService(UsuarioService usuarioService, ProductoService productoService) {
        this.usuarioService = usuarioService;
        this.productoService = productoService;
    }

    public Pedido iniciarPedido(Long usuarioId, FormaPago formaPago) {
        Usuario usuario = usuarioService.obtenerActivoPorId(usuarioId, "Usuario");
        if (formaPago == null) {
            throw new ValorInvalidoException("La forma de pago es obligatoria.");
        }
        return new Pedido(usuario, formaPago);
    }

    public DetallePedido agregarDetalle(Pedido pedido, Long productoId, int cantidad) {
        Producto producto = productoService.obtenerActivoPorId(productoId, "Producto");
        DetallePedido detalle = pedido.addDetallePedido(cantidad, producto.getPrecio(), producto);
        detalle.setId(detalleSecuencia++);
        return detalle;
    }

    public Pedido confirmarPedido(Pedido pedido) {
        if (pedido.getDetalles().isEmpty()) {
            throw new ValorInvalidoException("El pedido debe tener al menos un detalle.");
        }
        pedido.calcularTotal();
        guardarNuevo(pedido);
        pedido.getUsuario().agregarPedido(pedido);
        return pedido;
    }

    public Pedido actualizar(Long id, Estado estado, FormaPago formaPago) {
        Pedido pedido = obtenerActivoPorId(id, "Pedido");
        if (estado != null) {
            pedido.setEstado(estado);
        }
        if (formaPago != null) {
            pedido.setFormaPago(formaPago);
        }
        return pedido;
    }

    @Override
    public void eliminar(Long id, String nombreEntidad) {
        Pedido pedido = obtenerActivoPorId(id, nombreEntidad);
        pedido.setEliminado(true);
        pedido.getDetalles().forEach(detalle -> detalle.setEliminado(true));
    }
}
