/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.PerfilUsuario;
import entities.Usuario;
import enums.Rol;
import exception.DuplicadoException;
import exception.ValorInvalidoException;

/**
 *
 * @author emanu
 */
public class UsuarioService extends AbstractService<Usuario>{
    private long perfilSecuencia = 1L;

    public Usuario crear(String nombre, String apellido, String mail, String celular, String contrasena, Rol rol,
            String aliasPerfil) {
        validarUsuario(nombre, apellido, mail);
        validarMailUnico(mail, null);
        PerfilUsuario perfil = new PerfilUsuario(
                CategoriaService.estaVacio(aliasPerfil) ? nombre.trim().toLowerCase() : aliasPerfil.trim(),
                "Perfil creado junto al usuario");
        perfil.setId(perfilSecuencia++);
        Usuario usuario = new Usuario(nombre.trim(), apellido.trim(), mail.trim().toLowerCase(),
                CategoriaService.normalizar(celular), CategoriaService.normalizar(contrasena), rol, perfil);
        return guardarNuevo(usuario);
    }

    public Usuario editar(Long id, String nombre, String apellido, String mail, String celular, String contrasena,
            Rol rol, String aliasPerfil) {
        Usuario usuario = obtenerActivoPorId(id, "Usuario");
        if (!CategoriaService.estaVacio(nombre)) {
            usuario.setNombre(nombre.trim());
        }
        if (!CategoriaService.estaVacio(apellido)) {
            usuario.setApellido(apellido.trim());
        }
        if (!CategoriaService.estaVacio(mail)) {
            validarMailUnico(mail, id);
            usuario.setMail(mail.trim().toLowerCase());
        }
        if (!CategoriaService.estaVacio(celular)) {
            usuario.setCelular(celular.trim());
        }
        if (!CategoriaService.estaVacio(contrasena)) {
            usuario.setContrasena(contrasena.trim());
        }
        if (rol != null) {
            usuario.setRol(rol);
        }
        if (!CategoriaService.estaVacio(aliasPerfil)) {
            usuario.getPerfil().setAlias(aliasPerfil.trim());
        }
        return usuario;
    }

    private void validarUsuario(String nombre, String apellido, String mail) {
        CategoriaService.validarTexto(nombre, "El nombre del usuario es obligatorio.");
        CategoriaService.validarTexto(apellido, "El apellido del usuario es obligatorio.");
        CategoriaService.validarTexto(mail, "El mail del usuario es obligatorio.");
        if (!mail.contains("@") || !mail.contains(".")) {
            throw new ValorInvalidoException("El mail debe tener un formato basico valido.");
        }
    }

    private void validarMailUnico(String mail, Long idActual) {
        boolean existe = registros.stream()
                .filter(usuario -> !usuario.isEliminado())
                .anyMatch(usuario -> usuario.getMail().equalsIgnoreCase(mail.trim())
                        && !usuario.getId().equals(idActual));
        if (existe) {
            throw new DuplicadoException("Ya existe un usuario con ese mail.");
        }
    }
}
