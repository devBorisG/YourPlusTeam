package com.uco.yourplus.serviceyourplus.usecase.persona.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.repository.RepositoryCustomException;
import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.dtoyourplus.builder.RolDTO;
import com.uco.yourplus.entityyourplus.PersonaEntity;
import com.uco.yourplus.entityyourplus.RolEntity;
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

        List<PersonaEntity> result; //lista para almacenar resultados de consulta en la base de datos

        List<PersonaDomain> convertResult = new ArrayList<>(); //Lista para almacenar resultados convertidos

        if(domain.isPresent()){

            PersonaEntity personaEntity = new PersonaEntity();

            BeanUtils.copyProperties(domain.get(), personaEntity); //Copiar propiedades del objeto domain a personaEntity

            try{

                result = personaRepository.findCustom(personaEntity); //Realizar una consulta personalizada en la base de datos

            }catch (RepositoryCustomException e){
                throw ServiceCustomException.createTechnicalException(e, "Producto no encontrado");
            }
        }else {
            try{
                result = personaRepository.findAll(); //Consulta para obtener todas las personas en la base de datos
            }catch (RepositoryCustomException e){
                throw ServiceCustomException.createTechnicalException(e, "Productos no encontrados");
            }
        }
        //Iterar atravez de los resultados de la consulta
        for (PersonaEntity personaEntity : result){
            PersonaDomain personaDomain = new PersonaDomain();
            BeanUtils.copyProperties(personaEntity,personaDomain);

            List<RolEntity> rol = personaEntity.getRolEntity(); //Obtener una lista de los roles asociados a la persona

            List<RolDTO> rolDTOs = new ArrayList<>();

            //Iterar atravez de los roles y convertirlos en objetos RolDTO
            for(RolEntity rolEntity : rol){
                RolDTO rolDTO = new RolDTO();
                BeanUtils.copyProperties(rolEntity,rolDTO);
                rolDTOs.add(rolDTO);
            }

            personaDomain.setRolDTO(rolDTOs); //Establecer la lista de roles convertidos en personaDomain
            convertResult.add(personaDomain); //Agregar personaDomain a la lista de resultados convertidos

        }

        return convertResult;

    }

}
