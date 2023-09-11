package com.uco.yourplus.dtoyourplus.builder;

import java.util.UUID;

import static com.uco.yourplus.crosscuttingyourplus.helper.ObjectHelper.getDefaultIfNull;
import static com.uco.yourplus.crosscuttingyourplus.helper.StringHelper.EMPTY;
import static com.uco.yourplus.crosscuttingyourplus.helper.StringHelper.getDefaultString;
import static com.uco.yourplus.crosscuttingyourplus.helper.UUIDHelper.getDefaultUUID;
import static com.uco.yourplus.dtoyourplus.builder.rol.RolDTOBuilder.getRolDTOBuilder;

public class PersonaDTO {

    private UUID id;
    private String nombre;
    private String apellido;
    private String correo;
    private String password;
    private RolDTO rolDTO;

    public PersonaDTO(){
        setId(id);
        setNombre(EMPTY);
        setApellido(EMPTY);
        setCorreo(EMPTY);
        setPassword(EMPTY);
        setRolDTO(new RolDTO());
    }
    public PersonaDTO(final UUID id, final String nombre, final String apellido, final String correo,
                      final String password, final RolDTO rolDTO){
       setId(id);
       setNombre(nombre);
       setApellido(apellido);
       setCorreo(correo);
       setPassword(password);
       setRolDTO(rolDTO);
    }

    public static PersonaDTO create(final UUID id, final String nombre, final String apellido, final String correo,
                                    final String password, final RolDTO rolDTO){
        return new PersonaDTO(id,nombre,apellido,correo,password,rolDTO);
    }

    public static PersonaDTO create(final UUID id){
        return new PersonaDTO(id,EMPTY,EMPTY,EMPTY,EMPTY,new RolDTO());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = getDefaultUUID(id);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = getDefaultString(nombre);
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = getDefaultString(apellido);
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = getDefaultString(correo);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = getDefaultString(password);
    }

    public RolDTO getRolDTO() {
        return rolDTO;
    }

    public void setRolDTO(final RolDTO rolDTO) {
        this.rolDTO = getDefaultIfNull(rolDTO,getRolDTOBuilder().build());
    }
}
