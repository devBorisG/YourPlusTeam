package com.uco.yourplus.serviceyourplus.facade;

/**
 * Esta interfaz representa la fachada genérica para casos de uso en el sistema YourPlus.
 * Proporciona un método ejecutar que toma un tipo de objeto genérico T como parámetro.
 *
 * @param <T> El tipo de objeto correspondiente de DTO, que se utilizará como entrada para la ejecución del caso de uso.
 * @author David Andres
 */
public interface UseCaseFacade <T>{
    /**
     * Ejecuta un caso de uso utilizando un objeto de tipo T como entrada.
     *
     * @param dto El objeto de tipo T que contiene los datos necesarios para ejecutar el caso de uso.
     */
    void execute(T dto);
}
