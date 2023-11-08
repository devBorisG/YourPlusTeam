package com.uco.yourplus.apiyourplus.controller.persona;

import com.uco.yourplus.apiyourplus.controller.response.Response;
import com.uco.yourplus.dtoyourplus.builder.PersonaDTO;
import com.uco.yourplus.serviceyourplus.authentication.AuthenticationGeneric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/yourplus/v1/personas")
public class LoginPersonaController {

    @Autowired
    AuthenticationGeneric authentication;

    @PostMapping("/authenticate")
    public ResponseEntity<Response<PersonaDTO>> login(@RequestBody PersonaDTO personaDTO) {
        final Response<PersonaDTO> response = new Response<>();
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            String token = authentication.authenticate(personaDTO);
            response.setToken(token);
            response.addSuccesMessage("Bienvenido de nuevo");
        } catch (Exception exception) {
            httpStatus = HttpStatus.BAD_REQUEST;
            response.addErrorMessage("Acceso no autorizado");
        }
        return new ResponseEntity<>(response, httpStatus);
    }
}
