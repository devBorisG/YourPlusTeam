package com.uco.yourplus.repositoryyourplus.rol;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.crosscuttingyourplus.helper.StringHelper;
import com.uco.yourplus.crosscuttingyourplus.helper.UUIDHelper;
import com.uco.yourplus.entityyourplus.RolEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RolRepositoryCustomImpl implements RolRepositoryCustom{

    @PersistenceContext
    EntityManager entityManager;
    @Override
    public List<RolEntity> findCustom(RolEntity rolEntity) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<RolEntity> query = criteriaBuilder.createQuery(RolEntity.class);
            Root<RolEntity> rolEntityRoot = query.from(RolEntity.class);
            List<Predicate> predicates = new ArrayList<>();

            if(!Objects.isNull(rolEntity)){
                if(UUIDHelper.isDefaultUUID(rolEntity.getId())){
                    predicates.add(criteriaBuilder.equal(rolEntityRoot.get("id"),rolEntity.getId()));
                }
                if(!StringHelper.isEmpty(rolEntity.getDescripcion())){
                    predicates.add(criteriaBuilder.equal(rolEntityRoot.get("descripcion"),rolEntity.getDescripcion()));
                }
            }
            query.select(rolEntityRoot).where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
            return entityManager.createQuery(query).getResultList();
        } catch(Exception exception){
            throw ServiceCustomException.createTechnicalException(exception,"Se ha generado un error relacionado al rol");
        }
    }
}
