package com.uco.yourplus.repositoryYourPlus.rol;

import com.uco.yourplus.entityYourPlus.RolEntity;

import java.util.List;

public interface RolRepositoryCustom {
    List<RolEntity> findCustom(RolEntity rolEntity);
}
