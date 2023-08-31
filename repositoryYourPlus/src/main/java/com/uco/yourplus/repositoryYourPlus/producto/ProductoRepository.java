package com.uco.yourplus.repositoryYourPlus.producto;

import com.uco.yourplus.entityYourPlus.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, UUID>, ProductoRepositoryCustom {
}
