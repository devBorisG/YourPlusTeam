package com.uco.yourplus.repositoryyourplus.persona;

import com.uco.yourplus.crosscuttingyourplus.exceptions.repository.RepositoryCustomException;
import com.uco.yourplus.crosscuttingyourplus.helper.StringHelper;
import com.uco.yourplus.entityyourplus.PersonaEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Implementación personalizada del repositorio para consultas específicas relacionadas con la entidad PersonaEntity en el sistema YourPlus.
 * Esta implementación utiliza JPA Criteria API para construir consultas personalizadas basadas en los parámetros proporcionados.
 *
 * @see PersonaRepositoryCustom
 */

public class PersonaRepositoryCustomImpl implements PersonaRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Realiza una consulta personalizada en la entidad PersonaEntity en función de los parámetros proporcionados.
     *
     * @param personaEntity El objeto PersonaEntity que contiene los parámetros para la consulta personalizada.
     * @return Una lista de objetos PersonaEntity que coinciden con los parámetros de la consulta personalizada.
     * @throws RepositoryCustomException Sí ocurre un error durante la ejecución de la consulta personalizada.
     */
    @Override
    public List<PersonaEntity> findCustom(PersonaEntity personaEntity) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<PersonaEntity> query = criteriaBuilder.createQuery(PersonaEntity.class);
            Root<PersonaEntity> personaEntityRoot = query.from(PersonaEntity.class);
            List<Predicate> predicates = new ArrayList<>();

            if (!Objects.isNull(personaEntity)) {
                if (!StringHelper.isEmpty(personaEntity.getNombre())) {
                    predicates.add((criteriaBuilder.equal(personaEntityRoot.get("nombre"), personaEntity.getNombre())));
                }
                if (!StringHelper.isEmpty(personaEntity.getApellido())) {
                    predicates.add(criteriaBuilder.equal(personaEntityRoot.get("apellido"), personaEntity.getApellido()));
                }
                if (!StringHelper.isEmpty(personaEntity.getCorreo())) {
                    predicates.add(criteriaBuilder.equal(personaEntityRoot.get("correo"), personaEntity.getCorreo()));
                }
            }
            query.select(personaEntityRoot).where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
            return entityManager.createQuery(query).getResultList();
        } catch (Exception exception) {
            throw RepositoryCustomException.createTechnicalException(exception, "Ocurrio un error crenado el query para la consulta customizada");
        }
    }
}
