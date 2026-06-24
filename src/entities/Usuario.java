/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import enums.Rol;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author emanu
 */
public class Usuario extends BaseEntity{
    
    private String nombre;
    private String apellido;
    private String mail;
    private String celular;
    private String contrasena;
    private Rol rol;
    private PerfilUsuario perfil;
    private final List<Pedido> pedidos = new ArrayList<>();

    public Usuario(String nombre, String apellido, String mail, String celular, String contrasena, Rol rol,
            PerfilUsuario perfil) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.celular = celular;
        this.contrasena = contrasena;
        this.rol = rol;
        this.perfil = perfil;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public PerfilUsuario getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilUsuario perfil) {
        this.perfil = perfil;
    }

    public List<Pedido> getPedidos() {
        return Collections.unmodifiableList(pedidos);
    }

    public void agregarPedido(Pedido pedido) {
        if (!pedidos.contains(pedido)) {
            pedidos.add(pedido);
        }
    }

    @Override
    public String toString() {
        String alias = perfil == null ? "Sin perfil" : perfil.getAlias();
        return "Usuario{id=%d, nombre='%s', apellido='%s', mail='%s', celular='%s', rol=%s, perfil='%s'}"
                .formatted(getId(), nombre, apellido, mail, celular, rol, alias);
    }
    
}
