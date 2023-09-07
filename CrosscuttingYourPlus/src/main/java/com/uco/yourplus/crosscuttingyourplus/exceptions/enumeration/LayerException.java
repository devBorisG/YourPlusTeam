package com.uco.yourplus.crosscuttingyourplus.exceptions.enumeration;

/**
 * Enumeración que representa los diferentes niveles o capas de excepciones en una aplicación.
 * Los niveles pueden incluir SERVICE (servicio), CROSSCUTTING (aspectos transversales) y REPOSITORY (repositorio).
 */
public enum LayerException {
    /**
     * Nivel de excepción SERVICE (servicio).
     * Este nivel se utiliza para excepciones relacionadas con la capa de servicio de la aplicación.
     */
    SERVICE,

    /**
     * Nivel de excepción CROSSCUTTING (aspectos transversales).
     * Este nivel se utiliza para excepciones relacionadas con aspectos transversales de la aplicación, como la gestión de excepciones.
     */
    CROSSCUTTING,

    /**
     * Nivel de excepción REPOSITORY (repositorio).
     * Este nivel se utiliza para excepciones relacionadas con la capa de repositorio de la aplicación.
     */
    REPOSITORY
}

