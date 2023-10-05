package com.uco.yourplus.repositoryyourplus.producto;

import com.uco.yourplus.crosscuttingyourplus.exceptions.repository.RepositoryCustomException;
import com.uco.yourplus.crosscuttingyourplus.helper.StringHelper;
import com.uco.yourplus.entityyourplus.ProductosEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductosRepositoryCustomImpl implements ProductosRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ProductosEntity> findCustom(ProductosEntity productosEntity) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<ProductosEntity> query = criteriaBuilder.createQuery(ProductosEntity.class);
            Root<ProductosEntity> productosEntityRoot = query.from(ProductosEntity.class);
            List<Predicate> predicates = new ArrayList<>();

            if (!Objects.isNull(productosEntity)) {
                if (!StringHelper.isEmpty(productosEntity.getNombre())) {
                    predicates.add(criteriaBuilder.equal(productosEntityRoot.get("nombre"), productosEntity.getNombre()));
                }
                if (productosEntity.getPrecio() > 0) {
                    predicates.add(criteriaBuilder.equal(productosEntityRoot.get("precio"), productosEntity.getPrecio()));
                }
                if (!StringHelper.isEmpty(productosEntity.getDescripcion())) {
                    predicates.add(criteriaBuilder.equal(productosEntityRoot.get("descripcion"), productosEntity.getDescripcion()));
                }
                if (!StringHelper.isEmpty(productosEntity.getImagen())) {
                    predicates.add(criteriaBuilder.equal(productosEntityRoot.get("imagen"), productosEntity.getImagen()));
                }
            }

            query.select(productosEntityRoot).where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
            return entityManager.createQuery(query).getResultList();
        } catch (Exception exception) {
            throw RepositoryCustomException.createTechnicalException(exception, "Ocurrió un error creando el query para la consulta personalizada");
        }
    }

}
