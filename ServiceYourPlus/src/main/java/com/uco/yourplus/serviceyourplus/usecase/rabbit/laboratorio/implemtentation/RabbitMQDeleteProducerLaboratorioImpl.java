package com.uco.yourplus.serviceyourplus.usecase.rabbit.laboratorio.implemtentation;

import com.uco.yourplus.serviceyourplus.domain.LaboratorioDomain;
import com.uco.yourplus.serviceyourplus.domain.ProductoDomain;
import com.uco.yourplus.serviceyourplus.usecase.rabbit.laboratorio.RabbitMQDeleteProducerLaboratorio;
import com.uco.yourplus.serviceyourplus.usecase.rabbit.producto.RabbitMQDeleteProducerProducto;
import org.springframework.stereotype.Service;


@Service
public class RabbitMQDeleteProducerLaboratorioImpl implements RabbitMQDeleteProducerLaboratorio {


    @Override
    public void execute(LaboratorioDomain domain) {

    }
}
