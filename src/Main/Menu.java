/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import entities.Pedido;
import enums.Estado;
import enums.FormaPago;
import enums.Rol;
import exception.NegocioException;
import services.CategoriaService;
import services.PedidoService;
import services.ProductoService;
import services.UsuarioService;

/**
 *
 * @author emanu
 */
public class Menu {
    
    private final ConsoleReader reader;
    private final CategoriaService categoriaService;
    private final ProductoService productoService;
    private final UsuarioService usuarioService;
    private final PedidoService pedidoService;

    public Menu(ConsoleReader reader, CategoriaService categoriaService, ProductoService productoService,
            UsuarioService usuarioService, PedidoService pedidoService) {
        this.reader = reader;
        this.categoriaService = categoriaService;
        this.productoService = productoService;
        this.usuarioService = usuarioService;
        this.pedidoService = pedidoService;
    }

    public void iniciar() {
        int opcion;
        do {
            System.out.println("\n=== SISTEMA DE PEDIDOS (FOOD STORE) ===");
            System.out.println("1. Categorias");
            System.out.println("2. Productos");
            System.out.println("3. Usuarios");
            System.out.println("4. Pedidos");
            System.out.println("0. Salir");
            opcion = reader.leerEntero("Seleccione: ");
            try {
                switch (opcion) {
                    case 1 -> menuCategorias();
                    case 2 -> menuProductos();
                    case 3 -> menuUsuarios();
                    case 4 -> menuPedidos();
                    case 0 -> System.out.println("Gracias por usar Food Store.");
                    default -> System.out.println("Opcion fuera de rango.");
                }
            } catch (NegocioException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 0);
    }

    private void menuCategorias() {
        int opcion;
        do {
            System.out.println("\n--- Categorias ---");
            System.out.println("1. Listar");
            System.out.println("2. Crear");
            System.out.println("3. Editar");
            System.out.println("4. Eliminar");
            System.out.println("0. Volver");
            opcion = reader.leerEntero("Seleccione: ");
            switch (opcion) {
                case 1 -> listar("No hay categorias cargadas.", categoriaService.listarActivos());
                case 2 -> crearCategoria();
                case 3 -> editarCategoria();
                case 4 -> eliminarCategoria();
                case 0 -> { }
                default -> System.out.println("Opcion fuera de rango.");
            }
        } while (opcion != 0);
    }

    private void crearCategoria() {
        String nombre = reader.leerTextoObligatorio("Nombre: ");
        String descripcion = reader.leerTexto("Descripcion: ");
        System.out.println("Categoria creada con id " + categoriaService.crear(nombre, descripcion).getId() + ".");
    }

    private void editarCategoria() {
        listar("No hay categorias cargadas.", categoriaService.listarActivos());
        Long id = reader.leerLong("Id a editar: ");
        String nombre = reader.leerTexto("Nuevo nombre (Enter mantiene): ");
        String descripcion = reader.leerTexto("Nueva descripcion (Enter mantiene): ");
        System.out.println("Categoria actualizada: " + categoriaService.editar(id, nombre, descripcion));
    }

    private void eliminarCategoria() {
        listar("No hay categorias cargadas.", categoriaService.listarActivos());
        Long id = reader.leerLong("Id a eliminar: ");
        if (reader.confirmar("Confirma la baja logica")) {
            categoriaService.eliminar(id, "Categoria");
            System.out.println("Categoria eliminada logicamente.");
        }
    }

    private void menuProductos() {
        int opcion;
        do {
            System.out.println("\n--- Productos ---");
            System.out.println("1. Listar");
            System.out.println("2. Crear");
            System.out.println("3. Editar");
            System.out.println("4. Eliminar");
            System.out.println("0. Volver");
            opcion = reader.leerEntero("Seleccione: ");
            switch (opcion) {
                case 1 -> listar("No hay productos cargados.", productoService.listarActivos());
                case 2 -> crearProducto();
                case 3 -> editarProducto();
                case 4 -> eliminarProducto();
                case 0 -> { }
                default -> System.out.println("Opcion fuera de rango.");
            }
        } while (opcion != 0);
    }

    private void crearProducto() {
        listar("No hay categorias cargadas.", categoriaService.listarActivos());
        String nombre = reader.leerTextoObligatorio("Nombre: ");
        String descripcion = reader.leerTexto("Descripcion: ");
        double precio = reader.leerDouble("Precio: ");
        int stock = reader.leerEntero("Stock: ");
        String imagen = reader.leerTexto("Imagen: ");
        boolean disponible = reader.confirmar("Disponible");
        Long categoriaId = reader.leerLong("Id categoria: ");
        System.out.println("Producto creado con id "
                + productoService.crear(nombre, descripcion, precio, stock, imagen, disponible, categoriaId).getId()
                + ".");
    }

    private void editarProducto() {
        listar("No hay productos cargados.", productoService.listarActivos());
        Long id = reader.leerLong("Id a editar: ");
        String nombre = reader.leerTexto("Nuevo nombre (Enter mantiene): ");
        String descripcion = reader.leerTexto("Nueva descripcion (Enter mantiene): ");
        Double precio = leerDoubleOpcional("Nuevo precio (Enter mantiene): ");
        Integer stock = leerEnteroOpcional("Nuevo stock (Enter mantiene): ");
        String imagen = reader.leerTexto("Nueva imagen (Enter mantiene): ");
        Boolean disponible = reader.leerBooleanOpcional("Disponible");
        listar("No hay categorias cargadas.", categoriaService.listarActivos());
        Long categoriaId = leerLongOpcional("Nueva categoria id (Enter mantiene): ");
        System.out.println("Producto actualizado: "
                + productoService.editar(id, nombre, descripcion, precio, stock, imagen, disponible, categoriaId));
    }

    private void eliminarProducto() {
        listar("No hay productos cargados.", productoService.listarActivos());
        Long id = reader.leerLong("Id a eliminar: ");
        if (reader.confirmar("Confirma la baja logica")) {
            productoService.eliminar(id, "Producto");
            System.out.println("Producto eliminado logicamente.");
        }
    }

    private void menuUsuarios() {
        int opcion;
        do {
            System.out.println("\n--- Usuarios ---");
            System.out.println("1. Listar");
            System.out.println("2. Crear");
            System.out.println("3. Editar");
            System.out.println("4. Eliminar");
            System.out.println("0. Volver");
            opcion = reader.leerEntero("Seleccione: ");
            switch (opcion) {
                case 1 -> listar("No hay usuarios cargados.", usuarioService.listarActivos());
                case 2 -> crearUsuario();
                case 3 -> editarUsuario();
                case 4 -> eliminarUsuario();
                case 0 -> { }
                default -> System.out.println("Opcion fuera de rango.");
            }
        } while (opcion != 0);
    }

    private void crearUsuario() {
        String nombre = reader.leerTextoObligatorio("Nombre: ");
        String apellido = reader.leerTextoObligatorio("Apellido: ");
        String mail = reader.leerTextoObligatorio("Mail: ");
        String celular = reader.leerTexto("Celular: ");
        String contrasena = reader.leerTexto("Contrasena: ");
        Rol rol = seleccionarEnum(Rol.values(), "Rol");
        String alias = reader.leerTexto("Alias de perfil 1:1: ");
        System.out.println("Usuario creado con id "
                + usuarioService.crear(nombre, apellido, mail, celular, contrasena, rol, alias).getId() + ".");
    }

    private void editarUsuario() {
        listar("No hay usuarios cargados.", usuarioService.listarActivos());
        Long id = reader.leerLong("Id a editar: ");
        String nombre = reader.leerTexto("Nuevo nombre (Enter mantiene): ");
        String apellido = reader.leerTexto("Nuevo apellido (Enter mantiene): ");
        String mail = reader.leerTexto("Nuevo mail (Enter mantiene): ");
        String celular = reader.leerTexto("Nuevo celular (Enter mantiene): ");
        String contrasena = reader.leerTexto("Nueva contrasena (Enter mantiene): ");
        Rol rol = seleccionarEnumOpcional(Rol.values(), "Rol");
        String alias = reader.leerTexto("Nuevo alias de perfil (Enter mantiene): ");
        System.out.println("Usuario actualizado: "
                + usuarioService.editar(id, nombre, apellido, mail, celular, contrasena, rol, alias));
    }

    private void eliminarUsuario() {
        listar("No hay usuarios cargados.", usuarioService.listarActivos());
        Long id = reader.leerLong("Id a eliminar: ");
        if (reader.confirmar("Confirma la baja logica")) {
            usuarioService.eliminar(id, "Usuario");
            System.out.println("Usuario eliminado logicamente.");
        }
    }

    private void menuPedidos() {
        int opcion;
        do {
            System.out.println("\n--- Pedidos ---");
            System.out.println("1. Listar");
            System.out.println("2. Crear con detalles");
            System.out.println("3. Actualizar estado/forma de pago");
            System.out.println("4. Eliminar");
            System.out.println("0. Volver");
            opcion = reader.leerEntero("Seleccione: ");
            switch (opcion) {
                case 1 -> listar("No hay pedidos cargados.", pedidoService.listarActivos());
                case 2 -> crearPedido();
                case 3 -> actualizarPedido();
                case 4 -> eliminarPedido();
                case 0 -> { }
                default -> System.out.println("Opcion fuera de rango.");
            }
        } while (opcion != 0);
    }

    private void crearPedido() {
        listar("No hay usuarios cargados.", usuarioService.listarActivos());
        Long usuarioId = reader.leerLong("Id usuario: ");
        FormaPago formaPago = seleccionarEnum(FormaPago.values(), "Forma de pago");
        Pedido pedido = pedidoService.iniciarPedido(usuarioId, formaPago);
        boolean agregarMas;
        do {
            listar("No hay productos cargados.", productoService.listarActivos());
            Long productoId = reader.leerLong("Id producto: ");
            int cantidad = reader.leerEntero("Cantidad: ");
            pedidoService.agregarDetalle(pedido, productoId, cantidad);
            System.out.println("Detalle agregado. Total parcial: " + pedido.calcularTotal());
            agregarMas = reader.confirmar("Agregar otro detalle");
        } while (agregarMas);
        System.out.println("Pedido creado con id " + pedidoService.confirmarPedido(pedido).getId() + ".");
    }

    private void actualizarPedido() {
        listar("No hay pedidos cargados.", pedidoService.listarActivos());
        Long id = reader.leerLong("Id pedido: ");
        Estado estado = seleccionarEnumOpcional(Estado.values(), "Estado");
        FormaPago formaPago = seleccionarEnumOpcional(FormaPago.values(), "Forma de pago");
        System.out.println("Pedido actualizado: " + pedidoService.actualizar(id, estado, formaPago));
    }

    private void eliminarPedido() {
        listar("No hay pedidos cargados.", pedidoService.listarActivos());
        Long id = reader.leerLong("Id a eliminar: ");
        if (reader.confirmar("Confirma la baja logica")) {
            pedidoService.eliminar(id, "Pedido");
            System.out.println("Pedido eliminado logicamente.");
        }
    }

    private <T> void listar(String mensajeVacio, java.util.List<T> registros) {
        if (registros.isEmpty()) {
            System.out.println(mensajeVacio);
            return;
        }
        registros.forEach(System.out::println);
    }

    private <E extends Enum<E>> E seleccionarEnum(E[] valores, String titulo) {
        while (true) {
            imprimirOpcionesEnum(valores, titulo);
            int opcion = reader.leerEntero("Seleccione: ");
            if (opcion >= 1 && opcion <= valores.length) {
                return valores[opcion - 1];
            }
            System.out.println("Opcion fuera de rango.");
        }
    }

    private <E extends Enum<E>> E seleccionarEnumOpcional(E[] valores, String titulo) {
        while (true) {
            imprimirOpcionesEnum(valores, titulo + " (0 mantiene)");
            int opcion = reader.leerEntero("Seleccione: ");
            if (opcion == 0) {
                return null;
            }
            if (opcion >= 1 && opcion <= valores.length) {
                return valores[opcion - 1];
            }
            System.out.println("Opcion fuera de rango.");
        }
    }

    private <E extends Enum<E>> void imprimirOpcionesEnum(E[] valores, String titulo) {
        System.out.println(titulo + ":");
        for (int i = 0; i < valores.length; i++) {
            System.out.println((i + 1) + ". " + valores[i].name());
        }
    }

    private Double leerDoubleOpcional(String mensaje) {
        String texto = reader.leerTexto(mensaje).trim();
        return texto.isEmpty() ? null : Double.parseDouble(texto.replace(",", "."));
    }

    private Integer leerEnteroOpcional(String mensaje) {
        String texto = reader.leerTexto(mensaje).trim();
        return texto.isEmpty() ? null : Integer.parseInt(texto);
    }

    private Long leerLongOpcional(String mensaje) {
        String texto = reader.leerTexto(mensaje).trim();
        return texto.isEmpty() ? null : Long.parseLong(texto);
    }
    
}
