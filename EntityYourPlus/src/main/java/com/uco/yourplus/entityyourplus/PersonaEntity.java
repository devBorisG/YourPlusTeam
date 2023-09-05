package com.uco.yourplus.entityyourplus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Usuario")
public class PersonaEntity {
    @Id
    UUID id;
    String nombre;
    String apellido;
    String correo;
    String password;
    @ManyToOne
    @JoinColumn(name = "rol_id")
    RolEntity rolEntity;
}
