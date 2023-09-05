package com.uco.yourplus.dtoyourplus.builder.producto;

import java.util.UUID;

public interface ProductoBuilder {
    ProductoDTOBuilder setId(UUID id);
    ProductoDTOBuilder setNombre(String nombre);
    ProductoDTOBuilder setPrecio(int precio);
    ProductoDTOBuilder setDescripcion(String descripcion);
    ProductoDTOBuilder setImagen(String imagen);
}
