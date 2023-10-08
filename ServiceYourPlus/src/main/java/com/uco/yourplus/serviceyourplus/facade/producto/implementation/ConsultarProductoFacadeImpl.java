package com.uco.yourplus.serviceyourplus.facade.producto.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.crosscuttingyourplus.helper.IntegerHelper;
import com.uco.yourplus.crosscuttingyourplus.helper.StringHelper;
import com.uco.yourplus.dtoyourplus.builder.ProductoDTO;
import com.uco.yourplus.serviceyourplus.domain.ProductoDomain;
import com.uco.yourplus.serviceyourplus.facade.producto.ConsultarProductoFacade;
import com.uco.yourplus.serviceyourplus.usecase.producto.ConsultarProducto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ConsultarProductoFacadeImpl implements ConsultarProductoFacade {

    @Autowired
    private ConsultarProducto consultarProductos;

    @Override
    public List<ProductoDTO> execute(Optional<ProductoDTO> dto) {
        try {
            List<ProductoDomain> listDomain;
            if (dto.isPresent() &&
                    (!Objects.equals(dto.get().getNombre(), StringHelper.EMPTY) ||
                            !Objects.equals(dto.get().getPrecio(), IntegerHelper.ZERO) ||
                            !Objects.equals(dto.get().getDescripcion(), StringHelper.EMPTY) ||
                            !Objects.equals(dto.get().getImagen(), StringHelper.EMPTY))) {

                ProductoDomain productoDomain = new ProductoDomain();
                BeanUtils.copyProperties(dto.get(), productoDomain);
                listDomain = consultarProductos.execute(Optional.of(productoDomain));
            } else {
                listDomain = consultarProductos.execute(Optional.empty());
            }
            List<ProductoDTO> convertResult = new ArrayList<>();
            listDomain.forEach(value -> {
                ProductoDTO productoDTO = new ProductoDTO();
                BeanUtils.copyProperties(value, productoDTO);
                convertResult.add(productoDTO);
            });
            return convertResult;
        } catch (ServiceCustomException serviceCustomException) {
            throw serviceCustomException;
        } catch (Exception exception) {
            throw ServiceCustomException.createTechnicalException("Ocurrió un error intentando implementar el caso de uso de consultar productos" + ":" + exception.getMessage());
        }
    }

}
