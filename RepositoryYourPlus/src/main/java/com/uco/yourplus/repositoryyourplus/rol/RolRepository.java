package com.uco.yourplus.repositoryyourplus.rol;

import com.uco.yourplus.entityyourplus.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RolRepository extends JpaRepository<RolEntity, UUID>,RolRepositoryCustom {
}
