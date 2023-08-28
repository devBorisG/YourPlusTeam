package com.uco.yourplus.serviceYourPlus.usecase.rol.implementation;

import com.uco.yourplus.repositoryYourPlus.rol.RolRepository;
import com.uco.yourplus.serviceYourPlus.domain.RolDomain;
import com.uco.yourplus.serviceYourPlus.usecase.rol.ConsultarRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultarRolImpl implements ConsultarRol {

    @Autowired
    private RolRepository rolRepository;
    @Override
    public List<RolDomain> execute(Optional<RolDomain> domain) {
        return null;
    }
}
