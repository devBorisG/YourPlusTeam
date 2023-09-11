package com.uco.yourplus.serviceyourplus.facade.persona;

import com.uco.yourplus.dtoyourplus.builder.PersonaDTO;
import com.uco.yourplus.serviceyourplus.facade.UseCaseFacadePatch;

//public interface ActualizarPersonaFacade: Esto declara una interfaz pública llamada ActualizarPersonaFacade.Una interfaz es un conjunto de métodos abstractos (sin implementación)
// que deben ser implementados por cualquier clase que implemente esta interfaz.
//extends UseCaseFacadePatch<PersonaDTO>: hereda los métodos declarados en UseCaseFacadePatch, y PersonaDTO parece ser el tipo de
//datos que se espera que se utilice con esta interfaz.
public interface ActualizarPersonaFacade extends UseCaseFacadePatch<PersonaDTO> {
}