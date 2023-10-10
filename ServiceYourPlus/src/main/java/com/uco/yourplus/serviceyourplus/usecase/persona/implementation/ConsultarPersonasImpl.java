package com.uco.yourplus.serviceyourplus.usecase.persona.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.repository.RepositoryCustomException;
import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.entityyourplus.PersonaEntity;
import com.uco.yourplus.repositoryyourplus.persona.PersonaRepository;
import com.uco.yourplus.serviceyourplus.domain.PersonaDomain;
import com.uco.yourplus.serviceyourplus.domain.RolDomain;
import com.uco.yourplus.serviceyourplus.usecase.persona.ConsultarPersonas;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementación del caso de uso para consultar personas en el sistema YourPlus.
 * Este caso de uso permite buscar personas en el repositorio de personas y convertirlas en objetos de dominio PersonaDomain.
 *
 * @see ConsultarPersonas
 */
@Service
public class ConsultarPersonasImpl implements ConsultarPersonas {

    @Autowired
    private PersonaRepository personaRepository;

    /**
     * Ejecuta el caso de uso para consultar personas.
     *
     * @param domain Un objeto de dominio opcional que puede contener parámetros para la consulta.
     * @return Una lista de objetos PersonaDomain que representan las personas encontradas en la consulta.
     * @throws ServiceCustomException Sí ocurre un error en el servicio durante la consulta.
     * @throws RepositoryCustomException Sí ocurre un error en el repositorio durante la consulta.
     */
    @Override
    public List<PersonaDomain> execute(Optional<PersonaDomain> domain) {
        List<PersonaEntity> result;
        List<PersonaDomain> convertResult = new ArrayList<>();
        if(domain.isPresent()){
            PersonaEntity personaEntity = new PersonaEntity();
            BeanUtils.copyProperties(domain.get(), personaEntity);
            try{
                result = personaRepository.findCustom(personaEntity);

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

        result.forEach(value -> {
            PersonaDomain personaDomain = new PersonaDomain();
            RolDomain rolDomain = new RolDomain();
            BeanUtils.copyProperties(value.getRolEntity(), rolDomain);
            BeanUtils.copyProperties(value, personaDomain);
            personaDomain.setRolDomain(rolDomain);
            convertResult.add(personaDomain);
        });
        return convertResult;
    }
}
