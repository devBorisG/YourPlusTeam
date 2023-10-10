package com.uco.yourplus.serviceyourplus.specification.persona.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.dtoyourplus.builder.PersonaDTO;
import com.uco.yourplus.serviceyourplus.domain.PersonaDomain;
import com.uco.yourplus.serviceyourplus.specification.persona.PersonaDeleteSpecification;
import com.uco.yourplus.serviceyourplus.usecase.persona.ConsultarPersonas;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.uco.yourplus.dtoyourplus.builder.persona.PersonaDTOBuilder.getPersonaDTOBuilder;

@Service
public class PersonaDeleteSpecifiaction implements PersonaDeleteSpecification {

    @Autowired
    private ConsultarPersonas consultarPersonas;

    @Override
    public void isSatisfied(PersonaDomain data) {
        PersonaDTO dto = getPersonaDTOBuilder().build();
        PersonaDomain personaDomain = new PersonaDomain();
        dto.setCorreo(data.getCorreo());
        BeanUtils.copyProperties(dto, personaDomain);
        if (consultarPersonas.execute(Optional.of(personaDomain)).isEmpty()) {
            throw ServiceCustomException.createUserException("No existe el usuario");
        }
    }
}
