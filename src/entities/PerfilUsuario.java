/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author emanu
 */
public class PerfilUsuario extends BaseEntity{
    private String alias;
    private String observaciones;

    public PerfilUsuario(String alias, String observaciones) {
        this.alias = alias;
        this.observaciones = observaciones;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public String toString() {
        return "Perfil{id=%d, alias='%s', observaciones='%s'}".formatted(getId(), alias, observaciones);
    }
}
