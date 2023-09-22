package com.uco.yourplus.serviceyourplus.domain;

import com.uco.yourplus.dtoyourplus.builder.CategoriaDTO;
import com.uco.yourplus.dtoyourplus.builder.LaboratorioDTO;

import java.util.UUID;

public class ProductoDomain {

    UUID id;
    String nombre;
    int precio;
    String descripcion;
    String imagen;
    CategoriaDTO categoria;
    LaboratorioDTO laboratorio;

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

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(final int precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(final String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(final String imagen) {
        this.imagen = imagen;
    }

    public CategoriaDTO getCategoria() {
        return categoria;
    }

    public void setCategoria(final CategoriaDTO categoria) {
        this.categoria = categoria;
    }

    public LaboratorioDTO getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(final LaboratorioDTO laboratorio) {
        this.laboratorio = laboratorio;
    }
}
