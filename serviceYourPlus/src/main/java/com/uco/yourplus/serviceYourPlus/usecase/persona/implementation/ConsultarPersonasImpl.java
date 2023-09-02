package com.uco.yourplus.serviceYourPlus.usecase.persona.implementation;

import com.uco.yourplus.crosscuttingYourPlus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.entityYourPlus.PersonaEntity;
import com.uco.yourplus.repositoryYourPlus.persona.PersonaRepository;
import com.uco.yourplus.serviceYourPlus.domain.PersonaDomain;
import com.uco.yourplus.serviceYourPlus.usecase.persona.ConsultarPersonas;
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
            }catch (Exception e){//Aqui debe de estar la exceptio custom de repository
                throw ServiceCustomException.createTechnicalException(e, "no funca");
            }
        }else {
            try{
                result = personaRepository.findAll();
            }catch (Exception e){//Aqui debe de estar la exceptio custom de repository
                throw ServiceCustomException.createTechnicalException(e, "no funca x2");
            }
        }
        result.stream().map(value -> {
            return new PersonaDomain();
        }).forEach(convertResult::add);
        return convertResult;
    }
}
