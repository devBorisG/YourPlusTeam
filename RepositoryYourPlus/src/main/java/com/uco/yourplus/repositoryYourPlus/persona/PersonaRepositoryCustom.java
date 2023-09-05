package com.uco.yourplus.repositoryYourPlus.persona;

import com.uco.yourplus.entityYourPlus.PersonaEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepositoryCustom {
    List<PersonaEntity> findCustom(PersonaEntity personaEntity);
}
