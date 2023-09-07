package com.uco.yourplus.serviceyourplus.facade.persona.implementation;

import com.uco.yourplus.dtoyourplus.builder.PersonaDTO;
import com.uco.yourplus.serviceyourplus.domain.PersonaDomain;
import com.uco.yourplus.serviceyourplus.facade.persona.ActualizarPersonaPatchFacade;
import com.uco.yourplus.serviceyourplus.usecase.persona.ActualizarPersonaPatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActualizarPersonaPatchFacadeImpl implements ActualizarPersonaPatchFacade {

    @Autowired
    private ActualizarPersonaPatch useCase;

    @Override
    public void execute(PersonaDTO dto) {
        useCase.execute(new PersonaDomain());
    }
}
