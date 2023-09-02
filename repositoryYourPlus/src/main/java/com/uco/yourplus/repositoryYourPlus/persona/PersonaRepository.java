package com.uco.yourplus.repositoryYourPlus.persona;

import com.uco.yourplus.entityYourPlus.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PersonaRepository extends JpaRepository<PersonaEntity, UUID>, PersonaRepositoryCustom{
}
