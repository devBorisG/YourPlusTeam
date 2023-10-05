package com.uco.yourplus.serviceyourplus.specification.producto.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.repositoryyourplus.producto.ProductosRepository;
import com.uco.yourplus.serviceyourplus.domain.ProductosDomain;
import com.uco.yourplus.serviceyourplus.specification.producto.ProductosConsultarSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductosConsultarSpecificationImpl implements ProductosConsultarSpecification {

    @Autowired
    ProductosRepository productoRepository;

    @Override
    public void isSatisfied(ProductosDomain data) {
        verifyProductIntegrity(data);
    }

    private void verifyProductIntegrity(ProductosDomain productoDomain) {
        if (productoRepository.findById(productoDomain.getId()).isPresent()) {
            throw ServiceCustomException.createProductException("El producto ya existe");
        }

        // Verificar campos obligatorios que no pueden ser nulos
        if (productoDomain.getNombre() == null || productoDomain.getNombre().isEmpty()) {
            throw ServiceCustomException.createProductException("El nombre del producto es obligatorio");
        }

        if (productoDomain.getPrecio() <= 0) {
            throw ServiceCustomException.createProductException("El precio del producto debe ser mayor que cero");
        }

        // Otras verificaciones de campos obligatorios
        if (productoDomain.getDescripcion() == null || productoDomain.getDescripcion().isEmpty()) {
            throw ServiceCustomException.createProductException("La descripción del producto es obligatoria");
        }

        if (productoDomain.getImagen() == null || productoDomain.getImagen().isEmpty()) {
            throw ServiceCustomException.createProductException("La imagen del producto es obligatoria");
        }

    }

}
