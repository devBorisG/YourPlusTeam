package com.uco.yourplus.repositoryyourplus.producto;



import com.uco.yourplus.entityyourplus.ProductosEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductosRepositoryCustom {

    List<ProductosEntity> findCustom(ProductosEntity productosEntity);


}
