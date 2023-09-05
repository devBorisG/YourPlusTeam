package com.uco.yourplus.dtoyourplus.builder.persona;

import com.uco.yourplus.dtoyourplus.builder.RolDTO;

import java.util.UUID;

public interface PersonaBuilder {
    PersonaDTOBuilder setId(UUID id);
    PersonaDTOBuilder setNombre(String nombre);
    PersonaDTOBuilder setApellido(String apellido);
    PersonaDTOBuilder setCorreo(String correo);
    PersonaDTOBuilder setPassword(String password);
    PersonaDTOBuilder setRol(RolDTO rolDTO);
}
