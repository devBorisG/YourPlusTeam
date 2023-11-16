package com.uco.yourplus.serviceyourplus.facade.categoria.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.dtoyourplus.builder.CategoriaDTO;
import com.uco.yourplus.serviceyourplus.domain.CategoriaDomain;
import com.uco.yourplus.serviceyourplus.facade.categoria.SendListMessageFacade;
import com.uco.yourplus.serviceyourplus.usecase.categoria.SendListCategoriaMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SendListMessageFacadeImpl implements SendListMessageFacade {

    private final SendListCategoriaMessage useCase;


    public SendListMessageFacadeImpl(SendListCategoriaMessage useCase) {
        this.useCase = useCase;
    }

    @Override
    public List<CategoriaDTO> execute(Optional<CategoriaDTO> dto) {
        CategoriaDomain categoriaDomain = new CategoriaDomain();
        List<CategoriaDTO> convertResult = new ArrayList<>();
        try{
            BeanUtils.copyProperties(dto,categoriaDomain);
            List<CategoriaDomain> categoriaDomainList = useCase.execute(Optional.of(categoriaDomain));
            CategoriaDTO categoriaDTO = new CategoriaDTO();
            BeanUtils.copyProperties(categoriaDomain,categoriaDTO);
            convertResult.add(categoriaDTO);
            return convertResult;
        }catch (ServiceCustomException exception){
            throw exception;
        }catch (Exception exception){
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrio un error ejecutando el caso de uso de enviar mensaje para consultar las categorias");
        }
    }
}
