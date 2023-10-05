package com.uco.yourplus.dtoyourplus.builder.producto;

import com.uco.yourplus.dtoyourplus.builder.CategoriaDTO;
import com.uco.yourplus.dtoyourplus.builder.LaboratorioDTO;
import com.uco.yourplus.dtoyourplus.builder.ProductosDTO;

import java.util.UUID;

public class ProductosDTOBuilder implements ProductosBuilder {

    private UUID id;
    private String nombre;
    private int precio;
    private String descripcion;
    private String imagen;
    private CategoriaDTO categoria;
    private LaboratorioDTO laboratorio;

    private ProductosDTOBuilder(){
        super();
    }

    public static ProductosDTOBuilder getProductoDTOBuilder(){
        return new ProductosDTOBuilder();
    }

    @Override
    public ProductosDTOBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    @Override
    public ProductosDTOBuilder setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    @Override
    public ProductosDTOBuilder setPrecio(int precio) {
        this.precio = precio;
        return this;
    }

    @Override
    public ProductosDTOBuilder setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    @Override
    public ProductosDTOBuilder setImagen(String imagen) {
        this.imagen = imagen;
        return this;
    }

    @Override
    public ProductosDTOBuilder setCategoria(CategoriaDTO categoria) {
        this.categoria = categoria;
        return this;
    }

    @Override
    public ProductosDTOBuilder setLaboratorio(LaboratorioDTO laboratorio) {
        this.laboratorio = laboratorio;
        return this;
    }

    public ProductosDTO build(){
        return ProductosDTO.create(id,nombre,precio,descripcion,imagen,laboratorio,categoria);
    }
}
