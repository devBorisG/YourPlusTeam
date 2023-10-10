package com.uco.yourplus.serviceyourplus.facade.persona.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.crosscuttingyourplus.helper.StringHelper;
import com.uco.yourplus.dtoyourplus.builder.PersonaDTO;
import com.uco.yourplus.dtoyourplus.builder.RolDTO;
import com.uco.yourplus.serviceyourplus.domain.PersonaDomain;
import com.uco.yourplus.serviceyourplus.facade.persona.ConsultarPersonasFacade;
import com.uco.yourplus.serviceyourplus.usecase.persona.ConsultarPersonas;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Esta clase representa la implementación de la fachada para consultar personas.
 * Proporciona un método para ejecutar la consulta de una persona por medio de un DTO.
 *
 * @author David Andrés
 */
@Service
public class ConsultarPersonasFacadeImpl implements ConsultarPersonasFacade {

    @Autowired
    private ConsultarPersonas consultarPersonas;

    /**
     * Ejecuta la consulta de una persona utilizando un objeto PersonaDTO.
     *
     * @param dto El objeto PersonaDTO que contiene los datos de la persona a consultar.
     * @throws ServiceCustomException Sí ocurre un error personalizado en el servicio.
     */
    @Override
    public List<PersonaDTO> execute(Optional<PersonaDTO> dto) {
        try {
            List<PersonaDomain> listDomain;
            if (dto.isPresent() &&
                    (!Objects.equals(dto.get().getNombre(), StringHelper.EMPTY) ||
                            !Objects.equals(dto.get().getApellido(), StringHelper.EMPTY) ||
                            !Objects.equals(dto.get().getCorreo(), StringHelper.EMPTY))) {
                PersonaDomain personaDomain = new PersonaDomain();
                BeanUtils.copyProperties(dto.get(), personaDomain);
                listDomain = consultarPersonas.execute(Optional.of(personaDomain));
            } else {
                listDomain = consultarPersonas.execute(Optional.empty());
            }
            List<PersonaDTO> convertResult = new ArrayList<>();
            listDomain.forEach(value -> {
                PersonaDTO personaDTO = new PersonaDTO();
                RolDTO rolDTO = new RolDTO();
                BeanUtils.copyProperties(value.getRolDomain(), rolDTO);
                BeanUtils.copyProperties(value, personaDTO);
                personaDTO.setRolDTO(rolDTO);
                convertResult.add(personaDTO);
            });
            return convertResult;
        } catch (ServiceCustomException serviceCustomException) {
            throw serviceCustomException;
        } catch (Exception exception) {
            throw ServiceCustomException.createTechnicalException("Ocurrio un error intentando implementar el caso de uso de registrar usuarios" + ":" + exception.getMessage());
        }
    }
}
