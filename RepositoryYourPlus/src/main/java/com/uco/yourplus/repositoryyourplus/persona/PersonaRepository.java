package com.uco.yourplus.repositoryyourplus.persona;

import com.uco.yourplus.entityyourplus.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PersonaRepository extends JpaRepository<PersonaEntity, UUID>, PersonaRepositoryCustom{
}
