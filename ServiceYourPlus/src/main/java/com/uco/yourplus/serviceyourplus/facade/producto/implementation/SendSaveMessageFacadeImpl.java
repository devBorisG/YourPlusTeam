package com.uco.yourplus.serviceyourplus.facade.producto.implementation;

import com.uco.yourplus.dtoyourplus.builder.ProductoDTO;
import com.uco.yourplus.serviceyourplus.domain.ProductoDomain;
import com.uco.yourplus.serviceyourplus.facade.producto.SendMessageFacade;
import com.uco.yourplus.serviceyourplus.usecase.producto.SendRegistrarMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SendSaveMessageFacadeImpl implements SendMessageFacade {

    private final SendRegistrarMessage useCase;

    public SendSaveMessageFacadeImpl(SendRegistrarMessage useCase){
        this.useCase = useCase;
    }

    @Override
    public void execute(ProductoDTO dto) {
        ProductoDomain productoDomain = new ProductoDomain();
        BeanUtils.copyProperties(dto, productoDomain);
        useCase.execute(productoDomain);
    }
}
