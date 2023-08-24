package com.uco.yourplus.repositoryYourPlus.producto;

import com.uco.yourplus.crosscuttingYourPlus.helper.StringHelper;
import com.uco.yourplus.crosscuttingYourPlus.helper.UUIDHelper;
import com.uco.yourplus.entityYourPlus.ProductoEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductoRepositoryImpl implements ProductoRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ProductoEntity> findCustom(ProductoEntity productoEntity) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<ProductoEntity> query = criteriaBuilder.createQuery(ProductoEntity.class);
            Root<ProductoEntity> productoEntityRoot = query.from(ProductoEntity.class);
            List<Predicate> predicates = new ArrayList<>();

            if(!Objects.isNull(productoEntity)){
                if(!UUIDHelper.isDefaultUUID(productoEntity.getId())){
                    predicates.add(criteriaBuilder.equal(productoEntityRoot.get("id"),productoEntity.getId()));
                }
                if(!StringHelper.isEmpty(productoEntity.getNombre())){
                    predicates.add(criteriaBuilder.equal(productoEntityRoot.get("nombre"),productoEntity.getNombre()));
                }
            }

            query.select(productoEntityRoot).where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

            return entityManager.createQuery(query).getResultList();
        }catch (Exception exception){
            throw exception;
        }

    }
}
