package com.uco.yourplus.apiyourplus.controller.producto;


/*import com.uco.yourplus.apiyourplus.controller.response.Response;
import com.uco.yourplus.crosscuttingyourplus.exceptions.YourPlusCustomException;
import com.uco.yourplus.dtoyourplus.builder.CategoriaDTO;
import com.uco.yourplus.dtoyourplus.builder.LaboratorioDTO;
import com.uco.yourplus.dtoyourplus.builder.PersonaDTO;
import com.uco.yourplus.dtoyourplus.builder.ProductoDTO;
import com.uco.yourplus.serviceyourplus.facade.producto.ConsultarProductoFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("apiyourplus/producto")
public class ConsultarProductosController {

    @Autowired
    private ConsultarProductoFacade facade;

    @Autowired
    private LaboratorioRepository laboratorioRepository; // Repositorio de Laboratorio

    @Autowired
    private CategoriaRepository categoriaRepository; // Repositorio de Categoria

    @GetMapping()
    public ResponseEntity<Response<ProductoDTO>> execute(@RequestBody ProductoDTO productoDTO) {
        Optional<ProductoDTO> existDto = Optional.ofNullable(productoDTO);
        final Response<ProductoDTO> response = new Response<>();
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            List<ProductoDTO> data = facade.execute(existDto);

            // Consultar los datos de laboratorio y categoría y agregarlos al ProductoDTO
            for (ProductoDTO producto : data) {
                UUID laboratorioId = producto.getLaboratorioDTO().getId();
                UUID categoriaId = producto.getCategoriaDTO().getId();

                if (laboratorioId != null) {
                    // Consultar el laboratorio directamente desde el repositorio de Laboratorio
                    Optional<LaboratorioEntity> optionalLaboratorio = laboratorioRepository.findById(laboratorioId);
                    if (optionalLaboratorio.isPresent()) {
                        LaboratorioEntity laboratorio = optionalLaboratorio.get();
                        producto.getLaboratorioDTO().setNombre(laboratorio.getNombre()); // Establecer el nombre del laboratorio en ProductoDTO
                    }
                }

                if (categoriaId != null) {
                    // Consultar la categoría directamente desde el repositorio de Categoria
                    Optional<CategoriaEntity> optionalCategoria = categoriaRepository.findById(categoriaId);
                    if (optionalCategoria.isPresent()) {
                        CategoriaEntity categoria = optionalCategoria.get();
                        producto.getCategoriaDTO().setNombre(categoria.getNombre()); // Establecer el nombre de la categoría en ProductoDTO
                    }
                }
            }

            response.addSuccessMessage("Lista de productos");
            response.setData(data);
        } catch (final YourPlusCustomException yourPlusCustomException) {
            httpStatus = HttpStatus.BAD_REQUEST;
            if (yourPlusCustomException.isTechnicalException()) {
                response.addErrorMessage("Ocurrió un error consultando, intente nuevamente");
            } else {
                response.addErrorMessage(yourPlusCustomException.getMessage());
            }
        } catch (final Exception exception) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            response.addFatalMessage("Ocurrió un error interno en el servidor, intente nuevamente");
        }
        return new ResponseEntity<>(response, httpStatus);
    }
}*/





