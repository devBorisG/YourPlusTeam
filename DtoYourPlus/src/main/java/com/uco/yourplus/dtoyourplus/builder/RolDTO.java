package com.uco.yourplus.dtoyourplus.builder;

import java.util.UUID;

import static com.uco.yourplus.crosscuttingyourplus.helper.StringHelper.EMPTY;
import static com.uco.yourplus.crosscuttingyourplus.helper.StringHelper.getDefaultString;
import static com.uco.yourplus.crosscuttingyourplus.helper.UUIDHelper.getDefaultUUID;

public class RolDTO {

    private UUID id;
    private String descripcion;

    public RolDTO() {
        setId(getDefaultUUID(id));
        setDescripcion(EMPTY);
    }

    public RolDTO(final UUID id, final String descripcion) {
        setId(id);
        setDescripcion(descripcion);
    }

    public static RolDTO create(final UUID id, final String descripcion) {
        return new RolDTO(id, descripcion);
    }

    public static RolDTO create(final UUID id) {
        return new RolDTO(id, EMPTY);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = getDefaultUUID(id);
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = getDefaultString(descripcion);
    }
}
