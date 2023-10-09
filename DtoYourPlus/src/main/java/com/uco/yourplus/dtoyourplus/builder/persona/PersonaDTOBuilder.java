package com.uco.yourplus.dtoyourplus.builder.persona;

import com.uco.yourplus.dtoyourplus.builder.PersonaDTO;
import com.uco.yourplus.dtoyourplus.builder.RolDTO;

import java.util.List;
import java.util.UUID;

public class PersonaDTOBuilder implements PersonaBuilder{

    private UUID id;
    private String nombre;
    private String apellido;
    private String correo;
    private String password;
    private RolDTO rolDTO;

    private PersonaDTOBuilder(){
        super();
    }

    public static PersonaDTOBuilder getPersonaDTOBuilder(){
        return new PersonaDTOBuilder();
    }
    @Override
    public PersonaDTOBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    @Override
    public PersonaDTOBuilder setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    @Override
    public PersonaDTOBuilder setApellido(String apellido) {
        this.apellido = apellido;
        return this;
    }

    @Override
    public PersonaDTOBuilder setCorreo(String correo) {
        this.correo = correo;
        return this;
    }

    @Override
    public PersonaDTOBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public PersonaDTOBuilder setRol(RolDTO rolDTO) {
        this.rolDTO = rolDTO;
        return this;
    }

    public PersonaDTO build(){
        return PersonaDTO.create(id,nombre,apellido,correo,password,rolDTO);
    }
}
