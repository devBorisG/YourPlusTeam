package com.uco.yourplus.repositoryYourPlus.persona;

import com.uco.yourplus.crosscuttingYourPlus.helper.StringHelper;
import com.uco.yourplus.crosscuttingYourPlus.helper.UUIDHelper;
import com.uco.yourplus.entityYourPlus.PersonaEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PersonaRepositoryCustomImpl implements PersonaRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<PersonaEntity> findCustom(PersonaEntity personaEntity) {
        try{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<PersonaEntity> query = criteriaBuilder.createQuery(PersonaEntity.class);
            Root<PersonaEntity> personaEntityRoot = query.from(PersonaEntity.class);
            List<Predicate> predicates = new ArrayList<>();

            if(!Objects.isNull(personaEntity)){
                if(UUIDHelper.isDefaultUUID(personaEntity.getId())){
                    predicates.add(criteriaBuilder.equal(personaEntityRoot.get("id"),personaEntity.getId()));
                }
                if(!StringHelper.isEmpty(personaEntity.getNombre())){
                    predicates.add((criteriaBuilder.equal(personaEntityRoot.get("nombre"),personaEntity.getNombre())));
                }
                if(!StringHelper.isEmpty(personaEntity.getApellido())){
                    predicates.add(criteriaBuilder.equal(personaEntityRoot.get("apellido"),personaEntity.getApellido()));
                }
                if(!StringHelper.isEmpty(personaEntity.getCorreo())){
                    predicates.add(criteriaBuilder.equal(personaEntityRoot.get("correo"),personaEntity.getCorreo()));
                }
                if(!StringHelper.isEmpty(personaEntity.getPassword())){
                    predicates.add(criteriaBuilder.equal(personaEntityRoot.get("password"),personaEntity.getPassword()));
                }
                if(!Objects.isNull(personaEntity.getRolEntity())){
                    predicates.add(criteriaBuilder.equal(personaEntityRoot.get("rol"),personaEntity.getRolEntity()));
                }
            }
            query.select(personaEntityRoot).where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
            return entityManager.createQuery(query).getResultList();
        } catch (Exception exception){
            throw exception;
        }
    }
}
