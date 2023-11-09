package com.uco.yourplus.serviceyourplus.facade.producto.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.dtoyourplus.builder.CategoriaDTO;
import com.uco.yourplus.dtoyourplus.builder.LaboratorioDTO;
import com.uco.yourplus.dtoyourplus.builder.ProductoDTO;
import com.uco.yourplus.serviceyourplus.domain.ProductoDomain;
import com.uco.yourplus.serviceyourplus.facade.producto.SendListMessageFacade;
import com.uco.yourplus.serviceyourplus.usecase.producto.SendListProductMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class SendListMessageFacadeImpl implements SendListMessageFacade {

    private final SendListProductMessage useCase;

    public SendListMessageFacadeImpl(SendListProductMessage useCase) {
        this.useCase = useCase;
    }

    @Override
    public List<ProductoDTO> execute(Optional<ProductoDTO> dto) {
        ProductoDomain productoDomain = new ProductoDomain();
        List<ProductoDTO> convertResult = new ArrayList<>();
        try {
            BeanUtils.copyProperties(dto.get(), productoDomain);
            List<ProductoDomain> productoDomainList = useCase.execute(Optional.of(productoDomain));
            for (ProductoDomain domain : productoDomainList) {
                System.out.println(domain);
                ProductoDTO productoDTO = new ProductoDTO();
                LaboratorioDTO laboratorioDTO = new LaboratorioDTO();
                CategoriaDTO categoriaDTO = new CategoriaDTO();
                BeanUtils.copyProperties(domain.getCategoria(), categoriaDTO);
                BeanUtils.copyProperties(domain.getLaboratorio(), laboratorioDTO);
                BeanUtils.copyProperties(domain, productoDTO);
                productoDTO.setCategoria(categoriaDTO);
                productoDTO.setLaboratorio(laboratorioDTO);
                convertResult.add(productoDTO);
            }
            return convertResult;
        } catch (ServiceCustomException exception) {
            throw exception;
        } catch (Exception exception) {
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrió un error ejecutando el caso de uso de enviar mensaje para listar los productos");
        }
    }
}
