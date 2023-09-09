package com.uco.yourplus.repositoryyourplus.persona;

import com.uco.yourplus.entityyourplus.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repositorio de datos para la entidad PersonaEntity en el sistema YourPlus.
 * Esta interfaz extiende JpaRepository, que proporciona operaciones CRUD estándar para la entidad PersonaEntity.
 * También implementa PersonaRepositoryCustom, lo que permite definir consultas personalizadas para esta entidad.
 *
 * @see JpaRepository
 * @see PersonaRepositoryCustom
 */
@Repository
public interface PersonaRepository extends JpaRepository<PersonaEntity, UUID>,PersonaRepositoryCustom{
}
