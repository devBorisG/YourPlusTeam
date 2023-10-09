package com.uco.yourplus.serviceyourplus.domain;

import com.uco.yourplus.dtoyourplus.builder.CategoriaDTO;
import com.uco.yourplus.dtoyourplus.builder.LaboratorioDTO;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class ProductoDomain {

    UUID id;
    String nombre;
    int precio;
    String descripcion;
    String imagen;
    List<CategoriaDTO> categoria;
    List<LaboratorioDTO> laboratorio;

    public void setId(final UUID id) {
        this.id = id;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(final int precio) {
        this.precio = precio;
    }

    public void setDescripcion(final String descripcion) {
        this.descripcion = descripcion;
    }

    public void setImagen(final String imagen) {
        this.imagen = imagen;
    }

    public void setCategoria(List<CategoriaDTO> categoria) {
        this.categoria = categoria;
    }

    public void setLaboratorio(List<LaboratorioDTO> laboratorio) {
        this.laboratorio = laboratorio;
    }
}
