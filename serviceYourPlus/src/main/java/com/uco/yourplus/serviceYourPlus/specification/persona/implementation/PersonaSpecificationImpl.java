package com.uco.yourplus.serviceYourPlus.specification.persona.implementation;

import com.uco.yourplus.crosscuttingYourPlus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.repositoryYourPlus.persona.PersonaRepository;
import com.uco.yourplus.repositoryYourPlus.rol.RolRepository;
import com.uco.yourplus.serviceYourPlus.domain.PersonaDomain;
import com.uco.yourplus.serviceYourPlus.domain.RolDomain;
import com.uco.yourplus.serviceYourPlus.specification.persona.PersonaSpecification;
import com.uco.yourplus.serviceYourPlus.usecase.rol.ConsultarRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.uco.yourplus.crosscuttingYourPlus.helper.StringHelper.isEmpty;
import static com.uco.yourplus.crosscuttingYourPlus.helper.UUIDHelper.isDefaultUUID;

@Service
public class PersonaSpecificationImpl implements PersonaSpecification {

    @Autowired
    PersonaRepository repository;

    @Autowired
    RolRepository rolRepository;

    @Autowired
    ConsultarRol consultarRol;

    @Override
    public void isSatisfied(PersonaDomain data) {

    }

    private void verifyUserIntegrity(PersonaDomain personaDomain){
        if(repository.findById(personaDomain.getId()).isPresent());{
            throw ServiceCustomException.createUserException("El usuario ya existe");
        }
    }

    private void verifyRolIngegrity(RolDomain rolDomain){

    }

    private boolean verifyMandatoryPersonaAttributes(PersonaDomain personaDomain){
        return isDefaultUUID(personaDomain.getId()) || isEmpty(personaDomain.getNombre()) || isEmpty(personaDomain.getApellido()) ||
                isEmpty(personaDomain.getCorreo()) || isEmpty(personaDomain.getPassword());
    }
}
