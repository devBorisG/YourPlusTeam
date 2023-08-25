package com.uco.yourplus.dtoYourPlus.builder.rol;

import com.uco.yourplus.dtoYourPlus.builder.RolDTO;

import java.util.UUID;

public class RolDTOBuilder implements RolBuilder {

    private UUID id;
    private String descripcion;

    private RolDTOBuilder(){
        super();
    }

    public static RolDTOBuilder getRolDTOBuilder(){
        return new RolDTOBuilder();
    }

    @Override
    public RolDTOBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    @Override
    public RolDTOBuilder setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public RolDTO build(){
        return RolDTO.create(id,descripcion);
    }
}
