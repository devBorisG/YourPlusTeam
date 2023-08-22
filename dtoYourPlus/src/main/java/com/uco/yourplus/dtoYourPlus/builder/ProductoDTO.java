package com.uco.yourplus.dtoYourPlus.builder;

import java.util.UUID;

import static com.uco.yourplus.crosscuttingYourPlus.helper.IntegerHelper.ZERO;
import static com.uco.yourplus.crosscuttingYourPlus.helper.StringHelper.EMPTY;
import static com.uco.yourplus.crosscuttingYourPlus.helper.UUIDHelper.getDefaultUUID;

public class ProductoDTO {
    UUID id;
    String nombre;
    int precio;
    String descripcion;
    String imagen;

    public ProductoDTO(){
        setId(getDefaultUUID(id));
        setNombre(EMPTY);
        setPrecio(ZERO);
        setDescripcion(EMPTY);
        setImagen(EMPTY);
    }

    public ProductoDTO(final UUID id, final String nombre, final int precio, final String descripcion,
                        final String imagen){
        setId(id);
        setNombre(nombre);
        setPrecio(precio);
        setDescripcion(descripcion);
        setImagen(imagen);
    }

    public static ProductoDTO create(final UUID id, final String nombre, final int precio, final String descripcion,
                                        final String imagen){
        return new ProductoDTO(id, nombre, precio, descripcion, imagen);
    }

    public static ProductoDTO create(final UUID id){
        return new ProductoDTO(id,EMPTY,ZERO,EMPTY,EMPTY);
    }

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
}
