package com.uco.yourplus.serviceyourplus.usecase.persona;

import com.uco.yourplus.serviceyourplus.domain.PersonaDomain;
import com.uco.yourplus.serviceyourplus.usecase.UseCaseList;

/**
 * Esta interfaz representa un caso de uso para consultar personas en el sistema YourPlus.
 * Extiende la interfaz genérica UseCaseList, que define la ejecución de casos de uso que devuelven
 * una lista de objetos de dominio PersonaDomain.
 *
 * @see UseCaseList
 */
public interface ConsultarPersonas extends UseCaseList<PersonaDomain> {
}
