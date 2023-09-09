package com.uco.yourplus.serviceyourplus.specification.persona.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.dtoyourplus.builder.PersonaDTO;
import com.uco.yourplus.entityyourplus.RolEntity;
import com.uco.yourplus.repositoryyourplus.persona.PersonaRepository;
import com.uco.yourplus.repositoryyourplus.rol.RolRepository;
import com.uco.yourplus.serviceyourplus.domain.PersonaDomain;
import com.uco.yourplus.serviceyourplus.domain.RolDomain;
import com.uco.yourplus.serviceyourplus.specification.persona.PersonaSpecification;
import com.uco.yourplus.serviceyourplus.usecase.persona.ConsultarPersonas;
import com.uco.yourplus.serviceyourplus.usecase.rol.ConsultarRoles;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.uco.yourplus.crosscuttingyourplus.helper.StringHelper.*;
import static com.uco.yourplus.crosscuttingyourplus.helper.UUIDHelper.isDefaultUUID;
import static com.uco.yourplus.dtoyourplus.builder.persona.PersonaDTOBuilder.getPersonaDTOBuilder;

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
        verifyUserIntegrity(data);
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
        if(!verifyEmailDoesNotExist(personaDomain.getCorreo())){
            throw ServiceCustomException.createUserException("ya te registraste prro");
        }
        if(verifyRol(personaDomain)){
            throw ServiceCustomException.createUserException("No existe el rol");
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

    private boolean verifyIsLetters(PersonaDomain personaDomain){
        return isOnlyWordsAndSpace(personaDomain.getNombre()+personaDomain.getApellido());
    }

    private boolean verifyRol(PersonaDomain personaDomain){
        RolDomain rolDomain = new RolDomain();
        RolEntity rolEntity = new RolEntity();
        rolDomain.setDescripcion(personaDomain.getRolDTO().getDescripcion());
        BeanUtils.copyProperties(rolDomain,rolEntity);
        return rolRepository.findCustom(rolEntity).isEmpty();
    }
}
