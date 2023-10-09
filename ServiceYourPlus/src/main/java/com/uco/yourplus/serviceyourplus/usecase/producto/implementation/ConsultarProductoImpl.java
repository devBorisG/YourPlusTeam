package com.uco.yourplus.serviceyourplus.usecase.producto.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.repository.RepositoryCustomException;
import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.dtoyourplus.builder.CategoriaDTO;
import com.uco.yourplus.dtoyourplus.builder.LaboratorioDTO;
import com.uco.yourplus.entityyourplus.CategoriaEntity;
import com.uco.yourplus.entityyourplus.LaboratorioEntity;
import com.uco.yourplus.entityyourplus.ProductoEntity;
import com.uco.yourplus.repositoryyourplus.producto.ProductoRepository;
import com.uco.yourplus.serviceyourplus.domain.ProductoDomain;
import com.uco.yourplus.serviceyourplus.usecase.producto.ConsultarProducto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultarProductoImpl implements ConsultarProducto {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<ProductoDomain> execute(Optional<ProductoDomain> domain) {

        List<ProductoEntity> result; // Lista para almacenar resultados de consulta en la base de datos

        List<ProductoDomain> convertResult = new ArrayList<>(); // Lista para almacenar resultados convertidos

        if (domain.isPresent()) {

            ProductoEntity productoEntity = new ProductoEntity();

            BeanUtils.copyProperties(domain.get(), productoEntity); // Copiar propiedades del objeto domain a productoEntity

            try {

                result = productoRepository.findCustom(productoEntity); // Realizar una consulta personalizada en la base de datos

            } catch (RepositoryCustomException e) {
                throw ServiceCustomException.createTechnicalException(e, "Producto no encontrado");
            }
        } else {
            try {
                result = productoRepository.findAll(); // Consulta para obtener todos los productos en la base de datos
            } catch (RepositoryCustomException e) {
                throw ServiceCustomException.createTechnicalException(e, "Productos no encontrados");
            }
        }

        // Iterar a través de los resultados de la consulta
        for (ProductoEntity productoEntity : result) {
            ProductoDomain productoDomain = new ProductoDomain();
            BeanUtils.copyProperties(productoEntity, productoDomain);

            // Obtener una lista de laboratorios asociados al producto
            List<LaboratorioEntity> laboratorios = productoEntity.getLaboratorioEntity();

            // Obtener una lista de categorías asociadas al producto
            List<CategoriaEntity> categorias = productoEntity.getCategoriaEntity();

            // convertir las entidades LaboratorioEntity y CategoriaEntity en sus correspondientes DTOs si es necesario
            List<LaboratorioDTO> laboratoriosDTOs = new ArrayList<>();
            List<CategoriaDTO> categoriasDTOs = new ArrayList<>();

            for (LaboratorioEntity laboratorioEntity : laboratorios){
                LaboratorioDTO laboratorioDTO = new LaboratorioDTO();
                BeanUtils.copyProperties(laboratorioEntity,laboratorioDTO);
                laboratoriosDTOs.add(laboratorioDTO);
            }
            for (CategoriaEntity categoriaEntity : categorias){
                CategoriaDTO categoriaDTO = new CategoriaDTO();
                BeanUtils.copyProperties(categoriaEntity,categoriaDTO);
                categoriasDTOs.add(categoriaDTO);
            }


            productoDomain.setLaboratorio(laboratoriosDTOs); // Establecer la lista de laboratorios en productoDomain
            productoDomain.setCategoria(categoriasDTOs); // Establecer la lista de categorías en productoDomain
            convertResult.add(productoDomain); // Agregar productoDomain a la lista de resultados convertidos
        }

        return convertResult;
    }



}
