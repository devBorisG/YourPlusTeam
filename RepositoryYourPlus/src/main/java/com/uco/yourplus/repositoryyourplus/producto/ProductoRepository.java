package com.uco.yourplus.repositoryyourplus.producto;


import com.uco.yourplus.entityyourplus.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductoRepository extends JpaRepository<ProductoEntity, UUID>, ProductoRepositoryCustom {
}
