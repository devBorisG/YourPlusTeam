package com.uco.yourplus.repositoryyourplus.rol;

import com.uco.yourplus.entityyourplus.RolEntity;

import java.util.List;

public interface RolRepositoryCustom {
    List<RolEntity> findCustom(RolEntity rolEntity);
}
