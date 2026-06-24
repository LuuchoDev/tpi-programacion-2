/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.util.Scanner;

/**
 *
 * @author emanu
 */
public class ConsoleReader {
    private final Scanner scanner;

    public ConsoleReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    public String leerTextoObligatorio(String mensaje) {
        while (true) {
            String texto = leerTexto(mensaje).trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println("El valor no puede estar vacio.");
        }
    }

    public int leerEntero(String mensaje) {
        while (true) {
            try {
                return Integer.parseInt(leerTexto(mensaje).trim());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero entero valido.");
            }
        }
    }

    public Long leerLong(String mensaje) {
        while (true) {
            try {
                return Long.parseLong(leerTexto(mensaje).trim());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un id numerico valido.");
            }
        }
    }

    public double leerDouble(String mensaje) {
        while (true) {
            try {
                return Double.parseDouble(leerTexto(mensaje).replace(",", ".").trim());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero decimal valido.");
            }
        }
    }

    public Boolean leerBooleanOpcional(String mensaje) {
        while (true) {
            String valor = leerTexto(mensaje + " (S/N, Enter mantiene): ").trim();
            if (valor.isEmpty()) {
                return null;
            }
            if (valor.equalsIgnoreCase("S")) {
                return true;
            }
            if (valor.equalsIgnoreCase("N")) {
                return false;
            }
            System.out.println("Ingrese S o N.");
        }
    }

    public boolean confirmar(String mensaje) {
        while (true) {
            String valor = leerTexto(mensaje + " (S/N): ").trim();
            if (valor.equalsIgnoreCase("S")) {
                return true;
            }
            if (valor.equalsIgnoreCase("N")) {
                return false;
            }
            System.out.println("Ingrese S o N.");
        }
    }
}
