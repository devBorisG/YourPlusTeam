package com.uco.yourplus.serviceyourplus.usecase.persona.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.entityyourplus.PersonaEntity;
import com.uco.yourplus.repositoryyourplus.persona.PersonaRepository;
import com.uco.yourplus.serviceyourplus.usecase.persona.ActualizarPersonaPatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class ActualizarPersonaPatchImpl implements ActualizarPersonaPatch {

    @Autowired
    private PersonaRepository repository;

    @Override
    public void execute(Map<String, Object> domain) {
        try{
            Optional<PersonaEntity> personaEntityOptional = repository.findById((UUID) domain.get("id"));
            if (personaEntityOptional.isPresent()){
                PersonaEntity existingPersona = personaEntityOptional.get();

                if(domain.containsKey("nombre")){
                    existingPersona.setNombre((String) domain.get("nombre"));
                }

                if(domain.containsKey("apellido")){
                    existingPersona.setApellido((String) domain.get("apellido"));
                }

                if(domain.containsKey("password")){
                    existingPersona.setPassword((String) domain.get("password"));
                }

                repository.save(existingPersona);
            }else{
                throw ServiceCustomException.createUserException("La persona que pretende actualizar no existe en nuestra aplicacion");
            }
        } catch (EntityNotFoundException exception){
            throw ServiceCustomException.createUserException("La persona no existe");
        } catch (Exception exception){
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrio un error inesperado en el repositorio: "+ exception.getMessage());
        }
    }
}
