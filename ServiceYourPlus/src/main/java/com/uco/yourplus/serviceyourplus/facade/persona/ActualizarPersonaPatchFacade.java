package com.uco.yourplus.serviceyourplus.facade.persona;

import com.github.fge.jsonpatch.JsonPatch;
import com.uco.yourplus.serviceyourplus.facade.UseCaseFacadePatch;

/**
 * Interfaz que extiende la interfaz genérica UseCaseFacadePatch y se especializa en la ejecución de casos de uso
 * para la actualización parcial de entidades "Persona" utilizando operaciones de parcheo JsonPatch.
 * <p>
 * Esta interfaz define un contrato para la ejecución de casos de uso que implican la aplicación de operaciones
 * de parcheo JsonPatch a entidades "Persona".
 *
 * @see UseCaseFacadePatch
 */
public interface ActualizarPersonaPatchFacade extends UseCaseFacadePatch<JsonPatch> {
}
