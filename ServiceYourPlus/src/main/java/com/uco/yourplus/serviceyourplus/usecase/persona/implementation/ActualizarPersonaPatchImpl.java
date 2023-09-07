package com.uco.yourplus.serviceyourplus.usecase.persona.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.repository.RepositoryCustomException;
import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.crosscuttingyourplus.helper.StringHelper;
import com.uco.yourplus.entityyourplus.PersonaEntity;
import com.uco.yourplus.repositoryyourplus.persona.PersonaRepository;
import com.uco.yourplus.serviceyourplus.domain.PersonaDomain;
import com.uco.yourplus.serviceyourplus.usecase.persona.ActualizarPersonaPatch;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;
import java.util.Optional;

@Service
public class ActualizarPersonaPatchImpl implements ActualizarPersonaPatch {

    @Autowired
    private PersonaRepository repository;

    @Override
    public void execute(PersonaDomain domain) {
        try{
            Optional<PersonaEntity> personaEntity = repository.findById(domain.getId());
            if(personaEntity.isPresent()){
                personaEntity.get().getNombre()
            }else {
                throw RepositoryCustomException.createTechnicalException("Persona no encontrada");
            }
            PersonaDomain personaDomain = new PersonaDomain();
            PersonaEntity castResult = new PersonaEntity();
            BeanUtils.copyProperties(personaEntity, personaDomain);
            if (!Objects.equals(domain.getNombre(), personaDomain.getNombre())){
                personaDomain.setNombre(domain.getNombre());
            }

            if (!Objects.equals(domain.getApellido(), personaDomain.getApellido())){
                personaDomain.setApellido(domain.getApellido());
            }

            if(!Objects.equals(domain.getPassword(), personaDomain.getPassword())){
                personaDomain.setPassword(domain.getPassword());
            }
            BeanUtils.copyProperties(personaDomain, castResult);
            repository.saveAndFlush(castResult);

        }catch (EntityNotFoundException exception){
            throw ServiceCustomException.createUserException("Persona no encontrada");
        }catch (Exception exception){
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrio un error inesperado en la actulizacion de la persona: " + exception.getMessage());
        }
    }
}
