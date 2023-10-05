package com.uco.yourplus.repositoryyourplus.producto;


import com.uco.yourplus.entityyourplus.ProductosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductosRepository extends JpaRepository<ProductosEntity, UUID>, ProductosRepositoryCustom {
}
