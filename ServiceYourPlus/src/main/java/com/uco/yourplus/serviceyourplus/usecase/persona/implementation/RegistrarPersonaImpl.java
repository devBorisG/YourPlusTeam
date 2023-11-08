package com.uco.yourplus.serviceyourplus.usecase.persona.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.repository.RepositoryCustomException;
import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.entityyourplus.PersonaEntity;
import com.uco.yourplus.entityyourplus.RolEntity;
import com.uco.yourplus.repositoryyourplus.persona.PersonaRepository;
import com.uco.yourplus.serviceyourplus.domain.PersonaDomain;
import com.uco.yourplus.serviceyourplus.specification.persona.PersonaSpecification;
import com.uco.yourplus.serviceyourplus.usecase.persona.RegistrarPersona;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrarPersonaImpl implements RegistrarPersona {


    private final PersonaSpecification specification;

    private final PersonaRepository repository;

    private final PasswordEncoder passwordEncoder;

    public RegistrarPersonaImpl(PersonaSpecification specification, PersonaRepository repository, PasswordEncoder passwordEncoder) {
        this.specification = specification;
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void execute(PersonaDomain domain) {
        try {
            specification.isSatisfied(domain);
            domain.setPassword(passwordEncoder.encode(domain.getPassword()));
            PersonaEntity personaEntity = new PersonaEntity();
            RolEntity rolEntity = new RolEntity();
            BeanUtils.copyProperties(domain, personaEntity);
            BeanUtils.copyProperties(domain.getRolDomain(), rolEntity);
            personaEntity.setRolEntity(rolEntity);
            repository.save(personaEntity);
        } catch (ServiceCustomException exception) {
            throw exception;
        } catch (RepositoryCustomException exception) {
            throw ServiceCustomException.createTechnicalException(exception, "No se ha logrado ingresar con exito al nuevo usuario, por favor intente de nuevo");
        } catch (Exception e) {
            throw ServiceCustomException.createTechnicalException(e, "Error");
        }
    }
}
