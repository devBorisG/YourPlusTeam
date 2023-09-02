package com.uco.yourplus.serviceYourPlus.usecase.rol.implementation;

import com.uco.yourplus.entityYourPlus.RolEntity;
import com.uco.yourplus.repositoryYourPlus.rol.RolRepository;
import com.uco.yourplus.serviceYourPlus.domain.RolDomain;
import com.uco.yourplus.serviceYourPlus.usecase.rol.ConsultarRol;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConsultarRolImpl implements ConsultarRol {

    @Autowired
    private RolRepository rolRepository;
    @Override
    public List<RolDomain> execute(Optional<RolDomain> domain) {
        List<RolEntity> result;
        List<RolDomain> castResult = new ArrayList<>();
        try{
            result = rolRepository.findAll();
        } catch(Exception e){
            throw e;
        }
        result.stream().map(value -> {
            RolDomain rolDomain = new RolDomain();
            BeanUtils.copyProperties(value,rolDomain);
            return rolDomain;
        }).forEach(castResult::add);

        return castResult;
    }
}
