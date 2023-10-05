package com.uco.yourplus.serviceyourplus.usecase.producto.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.repository.RepositoryCustomException;
import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.entityyourplus.ProductosEntity;
import com.uco.yourplus.repositoryyourplus.producto.ProductosRepository;
import com.uco.yourplus.serviceyourplus.domain.ProductosDomain;
import com.uco.yourplus.serviceyourplus.usecase.producto.ConsultarProductos;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultarProductosImpl implements ConsultarProductos {

    @Autowired
    private ProductosRepository productoRepository;

    @Override
    public List<ProductosDomain> execute(Optional<ProductosDomain> domain) {
        List<ProductosEntity> result;
        List<ProductosDomain> convertResult = new ArrayList<>();

        if (domain.isPresent()) {
            // Si se proporciona un ProductoDomain, copia sus propiedades a una ProductoEntity
            ProductosEntity productoEntity = new ProductosEntity();
            BeanUtils.copyProperties(domain.get(), productoEntity);

            try {
                // Realiza una consulta personalizada utilizando la ProductoEntity proporcionada
                result = productoRepository.findCustom(productoEntity);
            } catch (RepositoryCustomException e) {
                // Maneja excepciones y lanza una ServiceCustomException si ocurre un error
                throw ServiceCustomException.createTechnicalException(e, "Error en la consulta de productos.");
            }
        } else {
            try {
                // Si no se proporciona un ProductoDomain, obtiene todos los productos
                result = productoRepository.findAll();
            } catch (RepositoryCustomException e) {
                // Maneja excepciones y lanza una ServiceCustomException si ocurre un error
                throw ServiceCustomException.createTechnicalException(e, "Error en la consulta de productos.");
            }
        }

        // Convierte los resultados de ProductoEntity a ProductoDomain
        result.forEach(value -> {
            ProductosDomain productoDomain = new ProductosDomain();
            BeanUtils.copyProperties(value, productoDomain);
            convertResult.add(productoDomain);
        });

        return convertResult;
    }


}
