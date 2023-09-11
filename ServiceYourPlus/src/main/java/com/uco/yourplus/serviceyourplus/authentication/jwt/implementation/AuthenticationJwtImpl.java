package com.uco.yourplus.serviceyourplus.authentication.jwt.implementation;

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
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationJwtImpl implements AuthenticationJwt {

    private final AuthenticationManager authenticationManager;

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    JwtService jwtService;

    @Override
    public String authenticate(PersonaDTO personaDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        personaDTO.getCorreo(),
                        personaDTO.getPassword()
                )
        );
        PersonaEntity personaEntity = new PersonaEntity();
        BeanUtils.copyProperties(personaDTO, personaEntity);
        PersonaEntity user = personaRepository.findByCorreo(personaEntity.getCorreo())
                .orElseThrow();

        return jwtService.generateToken(user);
    }
}
