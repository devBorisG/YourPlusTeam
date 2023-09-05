package com.uco.yourplus.serviceYourPlus.specification.persona.implementation;

import com.uco.yourplus.crosscuttingYourPlus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.dtoYourPlus.builder.PersonaDTO;
import com.uco.yourplus.serviceYourPlus.domain.PersonaDomain;
import com.uco.yourplus.serviceYourPlus.specification.persona.PersonaDeleteSpecification;
import com.uco.yourplus.serviceYourPlus.specification.persona.PersonaSpecification;
import com.uco.yourplus.serviceYourPlus.usecase.persona.ConsultarPersonas;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.uco.yourplus.dtoYourPlus.builder.persona.PersonaDTOBuilder.getPersonaDTOBuilder;

@Service
public class PersonaDeleteSpecifiaction implements PersonaDeleteSpecification{

    @Autowired
    private ConsultarPersonas consultarPersonas;
    @Override
    public void isSatisfied(PersonaDomain data) {
        PersonaDTO dto = getPersonaDTOBuilder().build();
        PersonaDomain personaDomain = new PersonaDomain();
        PersonaDTO personaDTO =  new PersonaDTO();
        dto.setCorreo(data.getCorreo());
        BeanUtils.copyProperties(personaDTO,personaDomain);
        if(consultarPersonas.execute(Optional.of(personaDomain)).isEmpty()){
            throw ServiceCustomException.createUserException("No existe el usuario");
        }
    }
}
