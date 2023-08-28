package com.uco.yourplus.serviceYourPlus.domain;

import java.util.UUID;

public class RolDomain {
    UUID id;
    String descripcion;

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(final String descripcion) {
        this.descripcion = descripcion;
    }
}
