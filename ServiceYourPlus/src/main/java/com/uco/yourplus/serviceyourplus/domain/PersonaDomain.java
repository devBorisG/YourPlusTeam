package com.uco.yourplus.serviceyourplus.domain;

import java.util.UUID;

public class PersonaDomain {

    UUID id;
    String nombre;
    String apellido;
    String correo;
    String password;
    RolDomain rolDomain;

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(final String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(final String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public RolDomain getRolDomain() {
        return rolDomain;
    }

    public void setRolDomain(final RolDomain rolDomain) {
        this.rolDomain = rolDomain;
    }
}
