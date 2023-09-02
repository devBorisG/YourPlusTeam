package com.uco.yourplus.serviceYourPlus.specification.persona.implementation;

import com.uco.yourplus.crosscuttingYourPlus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.dtoYourPlus.builder.PersonaDTO;
import com.uco.yourplus.repositoryYourPlus.persona.PersonaRepository;
import com.uco.yourplus.repositoryYourPlus.rol.RolRepository;
import com.uco.yourplus.serviceYourPlus.domain.PersonaDomain;
import com.uco.yourplus.serviceYourPlus.domain.RolDomain;
import com.uco.yourplus.serviceYourPlus.specification.persona.PersonaSpecification;
import com.uco.yourplus.serviceYourPlus.usecase.persona.ConsultarPersonas;
import com.uco.yourplus.serviceYourPlus.usecase.rol.ConsultarRol;
import com.uco.yourplus.serviceYourPlus.usecase.rol.ConsultarRoles;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.uco.yourplus.crosscuttingYourPlus.helper.ObjectHelper.isNull;
import static com.uco.yourplus.crosscuttingYourPlus.helper.StringHelper.*;
import static com.uco.yourplus.crosscuttingYourPlus.helper.UUIDHelper.isDefaultUUID;
import static com.uco.yourplus.dtoYourPlus.builder.persona.PersonaDTOBuilder.getPersonaDTOBuilder;

@Service
public class PersonaSpecificationImpl implements PersonaSpecification {

    @Autowired
    PersonaRepository repository;

    @Autowired
    RolRepository rolRepository;

    @Autowired
    ConsultarRoles consultarRoles;

    @Autowired
    ConsultarPersonas consultarPersonas;

    @Override
    public void isSatisfied(PersonaDomain data) {

    }

    private void verifyUserIntegrity(PersonaDomain personaDomain){
        if(repository.findById(personaDomain.getId()).isPresent()){
            throw ServiceCustomException.createUserException("El usuario ya existe");
        }
        if(verifyMandatoryPersonaAttributes(personaDomain)){
            throw ServiceCustomException.createUserException("C mamo");
        }
        if(!verifyIsLetters(personaDomain)){
            throw ServiceCustomException.createUserException("No son letricas");
        }
        if(!verifyEmail(personaDomain.getCorreo())){
            throw ServiceCustomException.createUserException("ta malo el correo");
        }
        if(verifyEmailDoesNotExist(personaDomain.getCorreo())){
            throw ServiceCustomException.createUserException("ya te registraste prro");
        }
    }


    private boolean verifyMandatoryPersonaAttributes(PersonaDomain personaDomain){
        return isDefaultUUID(personaDomain.getId()) || isEmpty(personaDomain.getNombre()) || isEmpty(personaDomain.getApellido()) ||
                isEmpty(personaDomain.getCorreo()) || isEmpty(personaDomain.getPassword());
    }

    private boolean verifyEmailDoesNotExist(String email) {
        PersonaDTO dto = getPersonaDTOBuilder().build();
        PersonaDomain personaDomain = new PersonaDomain();
        PersonaDTO personaDTO = new PersonaDTO();
        dto.setCorreo(email);
        BeanUtils.copyProperties(personaDTO, personaDomain);
        return consultarPersonas.execute(Optional.of(personaDomain)).isEmpty();
    }

    private boolean verifyObjectIsntNull(PersonaDomain personaDomain){
        return isNull(personaDomain.getRolDTO());
    }

    private boolean verifyIsLetters(PersonaDomain personaDomain){
        return isOnlyWordsAndSpace(personaDomain.getNombre()+personaDomain.getApellido());
    }
}
