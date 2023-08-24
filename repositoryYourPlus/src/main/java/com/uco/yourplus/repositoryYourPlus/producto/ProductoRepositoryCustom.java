package com.uco.yourplus.repositoryYourPlus.producto;

import com.uco.yourplus.entityYourPlus.ProductoEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepositoryCustom {
    List<ProductoEntity> findCustom(ProductoEntity productoEntity);
}
