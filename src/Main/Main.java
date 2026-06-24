/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import java.util.Scanner;
import services.CategoriaService;
import services.DataSeeder;
import services.PedidoService;
import services.ProductoService;
import services.UsuarioService;

/**
 *
 * @author emanu
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CategoriaService categoriaService = new CategoriaService();
        ProductoService productoService = new ProductoService(categoriaService);
        UsuarioService usuarioService = new UsuarioService();
        PedidoService pedidoService = new PedidoService(usuarioService, productoService);

        new DataSeeder(categoriaService, productoService, usuarioService, pedidoService).cargarDatosIniciales();

        try (Scanner scanner = new Scanner(System.in)) {
            ConsoleReader reader = new ConsoleReader(scanner);
            Menu menu = new Menu(reader, categoriaService, productoService, usuarioService, pedidoService);
            menu.iniciar();
        }
    }
    
}
