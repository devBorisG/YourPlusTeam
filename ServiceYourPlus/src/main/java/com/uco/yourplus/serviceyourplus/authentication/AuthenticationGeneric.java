package com.uco.yourplus.serviceyourplus.authentication;

import com.uco.yourplus.dtoyourplus.builder.PersonaDTO;

public interface AuthenticationGeneric {
    String authenticate(PersonaDTO personaDTO);

}
