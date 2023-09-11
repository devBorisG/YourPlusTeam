package com.uco.yourplus.serviceyourplus.authentication.jwt.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.dtoyourplus.builder.PersonaDTO;
import com.uco.yourplus.entityyourplus.PersonaEntity;
import com.uco.yourplus.repositoryyourplus.persona.PersonaRepository;
import com.uco.yourplus.repositoryyourplus.service.JwtService;
import com.uco.yourplus.serviceyourplus.authentication.jwt.AuthenticationJwt;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationJwtImpl implements AuthenticationJwt {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    JwtService jwtService;

    @Override
    public String authenticate(PersonaDTO personaDTO) {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            personaDTO.getCorreo(),
                            personaDTO.getPassword()
                    )
            );
        }catch (AuthenticationException e){
            throw ServiceCustomException.createTechnicalException(e, "Error en el authentication manager"+e.getMessage());
        }catch (Exception e){
            throw ServiceCustomException.createTechnicalException(e, "Error inesperado"+e.getMessage());
        }
        PersonaEntity personaEntity = new PersonaEntity();
        BeanUtils.copyProperties(personaDTO, personaEntity);
        PersonaEntity user = personaRepository.findByCorreo(personaEntity.getCorreo())
                .orElseThrow();
        return jwtService.generateToken(user);
    }
}
