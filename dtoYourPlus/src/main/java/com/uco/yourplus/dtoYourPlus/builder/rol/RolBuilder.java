package com.uco.yourplus.dtoYourPlus.builder.rol;

import java.util.UUID;

public interface RolBuilder {
    RolDTOBuilder setId(UUID id);
    RolDTOBuilder setDescripcion(String descripcion);
}
