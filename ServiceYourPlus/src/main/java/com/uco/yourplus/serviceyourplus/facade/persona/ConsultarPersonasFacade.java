package com.uco.yourplus.serviceyourplus.facade.persona;

import com.uco.yourplus.dtoyourplus.builder.PersonaDTO;
import com.uco.yourplus.serviceyourplus.facade.UseCaseFacade;
import com.uco.yourplus.serviceyourplus.facade.UseCaseFacadeList;

/**
 * Esta interfaz representa la fachada para consultar personas en el sistema YourPlus.
 * Implementa la interfaz genérica UseCaseFacade, que define métodos para ejecutar casos de uso
 * relacionados con la gestión de personas.
 * <p>
 * La implementación concreta de esta interfaz proporcionará una forma estandarizada de acceder al
 * caso de uso para consultar información de personas, utilizando objetos PersonaDTO.
 *
 * @author David Andrés
 * @see UseCaseFacade
 */
public interface ConsultarPersonasFacade extends UseCaseFacadeList<PersonaDTO> {
}
