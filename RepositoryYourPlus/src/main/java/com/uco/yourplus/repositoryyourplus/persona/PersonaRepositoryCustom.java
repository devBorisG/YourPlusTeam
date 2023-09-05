package com.uco.yourplus.repositoryyourplus.persona;

import com.uco.yourplus.entityyourplus.PersonaEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepositoryCustom {
    List<PersonaEntity> findCustom(PersonaEntity personaEntity);
}
