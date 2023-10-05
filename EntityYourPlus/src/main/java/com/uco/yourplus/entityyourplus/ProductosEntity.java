package com.uco.yourplus.entityyourplus;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Producto", schema = "public")

public class ProductosEntity {

    @Id
    UUID id;
    String nombre;
    int precio;
    String descripcion;
    String imagen;

    @JsonProperty("id")
    public void setId(UUID id) {
        this.id = id;
    }

    @JsonProperty("nombre")
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @JsonProperty("precio")
    public void setPrecio(int precio){
        this.precio = precio;
    }

    @JsonProperty("descripcion")
    public void setdescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    @JsonProperty("imagen")
    public void setimagen(String imagen){
        this.imagen = imagen;
    }

    
}
