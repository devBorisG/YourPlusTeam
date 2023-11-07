package com.uco.yourplus.serviceyourplus.usecase.persona.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.repository.RepositoryCustomException;
import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.entityyourplus.PersonaEntity;
import com.uco.yourplus.entityyourplus.RolEntity;
import com.uco.yourplus.repositoryyourplus.persona.PersonaRepository;
import com.uco.yourplus.repositoryyourplus.service.JwtService;
import com.uco.yourplus.serviceyourplus.domain.PersonaDomain;
import com.uco.yourplus.serviceyourplus.specification.persona.PersonaSpecification;
import com.uco.yourplus.serviceyourplus.usecase.persona.RegistrarPersona;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrarPersonaImpl implements RegistrarPersona {

    @Autowired
    PersonaSpecification specification;
    @Autowired
    private PersonaRepository repository;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String execute(PersonaDomain domain) {
        try {
            specification.isSatisfied(domain);
            domain.setPassword(passwordEncoder.encode(domain.getPassword()));
            PersonaEntity personaEntity = new PersonaEntity();
            RolEntity rolEntity = new RolEntity();
            BeanUtils.copyProperties(domain, personaEntity);
            BeanUtils.copyProperties(domain.getRolDomain(), rolEntity);
            personaEntity.setRolEntity(rolEntity);
            repository.save(personaEntity);
            return jwtService.generateToken(personaEntity);
        } catch (ServiceCustomException exception) {
            throw exception;
        } catch (RepositoryCustomException exception) {
            throw ServiceCustomException.createTechnicalException(exception, "No se ha logrado ingresar con exito al nuevo usuario, por favor intente de nuevo");
        } catch (Exception e) {
            throw ServiceCustomException.createTechnicalException(e, "Error");
        }
    }
}
