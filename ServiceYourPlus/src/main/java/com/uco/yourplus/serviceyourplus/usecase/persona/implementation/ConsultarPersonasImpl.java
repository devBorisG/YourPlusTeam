package com.uco.yourplus.serviceyourplus.usecase.persona.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.repository.RepositoryCustomException;
import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.entityyourplus.PersonaEntity;
import com.uco.yourplus.repositoryyourplus.persona.PersonaRepository;
import com.uco.yourplus.serviceyourplus.domain.PersonaDomain;
import com.uco.yourplus.serviceyourplus.usecase.persona.ConsultarPersonas;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultarPersonasImpl implements ConsultarPersonas {

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public List<PersonaDomain> execute(Optional<PersonaDomain> domain) {
        List<PersonaEntity> result;
        List<PersonaDomain> convertResult = new ArrayList<>();
        if(domain.isPresent()){
            PersonaEntity personaEntity = new PersonaEntity();
            BeanUtils.copyProperties(domain, personaEntity);
            try{
                result = personaRepository.findCustom(personaEntity);
                if (result.isEmpty()){
                    throw ServiceCustomException.createUserException("Usuario no existe");
                }
            }catch (RepositoryCustomException e){
                throw ServiceCustomException.createTechnicalException(e, "no funca");
            }
        }else {
            try{
                result = personaRepository.findAll();
            }catch (RepositoryCustomException e){
                throw ServiceCustomException.createTechnicalException(e, "no funca x2");
            }
        }
        result.stream().map(value -> new PersonaDomain()).forEach(convertResult::add);
        return convertResult;
    }
}
