package com.uco.yourplus.dtoyourplus.builder.producto;

import com.uco.yourplus.dtoyourplus.builder.CategoriaDTO;
import com.uco.yourplus.dtoyourplus.builder.LaboratorioDTO;

import java.util.UUID;

public interface ProductosBuilder {

    ProductosDTOBuilder setId(UUID id);
    ProductosDTOBuilder setNombre(String nombre);
    ProductosDTOBuilder setPrecio(int precio);
    ProductosDTOBuilder setDescripcion(String descripcion);
    ProductosDTOBuilder setImagen(String imagen);
    ProductosDTOBuilder setCategoria(CategoriaDTO categoria);
    ProductosDTOBuilder setLaboratorio(LaboratorioDTO laboratorio);
}
