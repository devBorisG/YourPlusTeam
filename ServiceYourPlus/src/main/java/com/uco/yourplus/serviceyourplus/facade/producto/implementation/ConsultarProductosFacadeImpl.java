package com.uco.yourplus.serviceyourplus.facade.producto.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.crosscuttingyourplus.helper.IntegerHelper;
import com.uco.yourplus.crosscuttingyourplus.helper.StringHelper;
import com.uco.yourplus.dtoyourplus.builder.ProductosDTO;
import com.uco.yourplus.serviceyourplus.domain.ProductosDomain;
import com.uco.yourplus.serviceyourplus.facade.producto.ConsultarProductosFacade;
import com.uco.yourplus.serviceyourplus.usecase.producto.ConsultarProductos;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ConsultarProductosFacadeImpl implements ConsultarProductosFacade {

    @Autowired
    private ConsultarProductos consultarProductos;

    @Override
    public List<ProductosDTO> execute(Optional<ProductosDTO> dto) {
        try {
            List<ProductosDomain> listDomain;
            if (dto.isPresent() &&
                    (!Objects.equals(dto.get().getNombre(), StringHelper.EMPTY) ||
                            !Objects.equals(dto.get().getPrecio(), IntegerHelper.ZERO) ||
                            !Objects.equals(dto.get().getDescripcion(), StringHelper.EMPTY) ||
                            !Objects.equals(dto.get().getImagen(), StringHelper.EMPTY))) {

                ProductosDomain productoDomain = new ProductosDomain();
                BeanUtils.copyProperties(dto.get(), productoDomain);
                listDomain = consultarProductos.execute(Optional.of(productoDomain));
            } else {
                listDomain = consultarProductos.execute(Optional.empty());
            }
            List<ProductosDTO> convertResult = new ArrayList<>();
            listDomain.forEach(value -> {
                ProductosDTO productoDTO = new ProductosDTO();
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
