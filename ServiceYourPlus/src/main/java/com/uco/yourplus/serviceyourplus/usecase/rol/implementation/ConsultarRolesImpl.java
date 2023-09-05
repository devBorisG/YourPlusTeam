package com.uco.yourplus.serviceyourplus.usecase.rol.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.repository.RepositoryCustomException;
import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.entityyourplus.RolEntity;
import com.uco.yourplus.repositoryyourplus.rol.RolRepository;
import com.uco.yourplus.serviceyourplus.domain.RolDomain;
import com.uco.yourplus.serviceyourplus.usecase.rol.ConsultarRoles;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultarRolesImpl implements ConsultarRoles {  

    @Autowired
    private RolRepository rolRepository;

    @Override
    public List<RolDomain> execute(Optional<RolDomain> domain) {
        List<RolEntity> result;
        List<RolDomain> castResult = new ArrayList<>();

        if (domain.isPresent()){
            try{
                RolEntity rolEntity = new RolEntity();
                BeanUtils.copyProperties(domain, rolEntity);
                result = rolRepository.findCustom(rolEntity);
            }catch (RepositoryCustomException e){
                throw ServiceCustomException.createTechnicalException(e, "no funca");
            }
        }else {
            try{
                result = rolRepository.findAll();
            }catch (RepositoryCustomException e){
                throw ServiceCustomException.createTechnicalException(e, "no funca");
            }
        }
        result.stream().map(value -> {
            return new RolDomain();
        }).forEach(castResult::add);
        return castResult;
    }
}
