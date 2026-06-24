/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.Pedido;
import enums.FormaPago;
import enums.Rol;

/**
 *
 * @author emanu
 */
public class DataSeeder {
    private final CategoriaService categoriaService;
    private final ProductoService productoService;
    private final UsuarioService usuarioService;
    private final PedidoService pedidoService;

    public DataSeeder(CategoriaService categoriaService, ProductoService productoService,
            UsuarioService usuarioService, PedidoService pedidoService) {
        this.categoriaService = categoriaService;
        this.productoService = productoService;
        this.usuarioService = usuarioService;
        this.pedidoService = pedidoService;
    }

    public void cargarDatosIniciales() {
        categoriaService.crear("Hamburguesas", "Comidas principales con pan artesanal");
        categoriaService.crear("Bebidas", "Gaseosas, aguas y jugos");
        productoService.crear("Burger Clasica", "Carne, queso y salsa de la casa", 6500, 20, "burger.jpg", true, 1L);
        productoService.crear("Limonada", "Limonada natural de 500 ml", 1800, 30, "limonada.jpg", true, 2L);
        usuarioService.crear("Ana", "Gomez", "ana.gomez@mail.com", "1122334455", "1234", Rol.USUARIO, "ana");
        usuarioService.crear("Admin", "Food", "admin@foodstore.com", "1100000000", "admin", Rol.ADMIN, "admin");
        Pedido pedido = pedidoService.iniciarPedido(1L, FormaPago.EFECTIVO);
        pedidoService.agregarDetalle(pedido, 1L, 2);
        pedidoService.agregarDetalle(pedido, 2L, 1);
        pedidoService.confirmarPedido(pedido);
    }
}
