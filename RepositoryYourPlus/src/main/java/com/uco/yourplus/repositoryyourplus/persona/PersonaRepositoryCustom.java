package com.uco.yourplus.repositoryyourplus.persona;

import com.uco.yourplus.entityyourplus.PersonaEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interfaz personalizada para consultas personalizadas relacionadas con la entidad PersonaEntity en el sistema YourPlus.
 * Las implementaciones de esta interfaz permiten definir consultas específicas que no están cubiertas por los métodos estándar
 * proporcionados por JpaRepository.
 */
@Repository
public interface PersonaRepositoryCustom {
    /**
     * Realiza una consulta personalizada basada en los parámetros proporcionados y devuelve una lista de resultados.
     *
     * @param personaEntity El objeto PersonaEntity que contiene los parámetros para la consulta personalizada.
     * @return Una lista de objetos PersonaEntity que coinciden con los parámetros de la consulta personalizada.
     */
    List<PersonaEntity> findCustom(PersonaEntity personaEntity);
}
