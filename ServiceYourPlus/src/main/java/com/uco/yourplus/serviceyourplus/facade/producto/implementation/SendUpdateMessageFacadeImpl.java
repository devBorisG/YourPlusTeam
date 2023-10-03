package com.uco.yourplus.serviceyourplus.facade.producto.implementation;
import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.dtoyourplus.builder.ProductoDTO;
import com.uco.yourplus.serviceyourplus.domain.ProductoDomain;
import com.uco.yourplus.serviceyourplus.facade.producto.SendUpdateMessageFacade;
import com.uco.yourplus.serviceyourplus.usecase.producto.SendUpdateProductMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SendUpdateMessageFacadeImpl implements SendUpdateMessageFacade {

    private final SendUpdateProductMessage useCase;

    public SendUpdateMessageFacadeImpl(SendUpdateProductMessage useCase) {
        this.useCase = useCase;
    }

    @Override
    public void execute(ProductoDTO dto) {
        ProductoDomain productoDomain = new ProductoDomain();
        try {
            BeanUtils.copyProperties(dto, productoDomain);
            useCase.execute(productoDomain);
        }catch (ServiceCustomException exception){
            throw exception;
        }catch (Exception exception){
            throw ServiceCustomException.createTechnicalException(exception,"Ocurrió un error ejecutando el caso de uso de enviar mensaje para actualizar el producto");
        }
    }
}
