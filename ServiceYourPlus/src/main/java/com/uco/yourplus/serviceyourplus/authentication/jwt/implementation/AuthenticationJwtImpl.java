package com.uco.yourplus.serviceyourplus.authentication.jwt.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.dtoyourplus.builder.PersonaDTO;
import com.uco.yourplus.entityyourplus.PersonaEntity;
import com.uco.yourplus.entityyourplus.RolEntity;
import com.uco.yourplus.repositoryyourplus.persona.PersonaRepository;
import com.uco.yourplus.repositoryyourplus.service.JwtService;
import com.uco.yourplus.serviceyourplus.authentication.jwt.AuthenticationJwt;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationJwtImpl implements AuthenticationJwt {


    private final PersonaRepository personaRepository;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @Override
    public String authenticate(PersonaDTO personaDTO) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            personaDTO.getCorreo(),
                            personaDTO.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            throw ServiceCustomException.createTechnicalException(e, "Error en el authentication manager" + e.getMessage());
        } catch (Exception e) {
            throw ServiceCustomException.createTechnicalException(e, "Error inesperado" + e.getMessage());
        }
        PersonaEntity personaEntity = new PersonaEntity();
        RolEntity rolEntity = new RolEntity();
        BeanUtils.copyProperties(personaDTO, personaEntity);
        BeanUtils.copyProperties(personaDTO.getRolDTO(),rolEntity);
        personaEntity.setRolEntity(rolEntity);
        PersonaEntity user = personaRepository.findByCorreo(personaEntity.getCorreo())
                .orElseThrow();
        return jwtService.generateToken(user);
    }
}
