package com.uco.yourplus.serviceyourplus.usecase.microservices.procesador.response.process;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.crosscuttingyourplus.helper.json.MapperJsonObject;
import com.uco.yourplus.serviceyourplus.domain.ProductoDomain;
import com.uco.yourplus.serviceyourplus.domain.enumeration.StateResponse;
import com.uco.yourplus.serviceyourplus.domain.response.ResponseProductoDomain;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class ProcessRabbitResponseProducto {
    private final MapperJsonObject mapperJsonObject;

    public ProcessRabbitResponseProducto(MapperJsonObject mapperJsonObject) {
        this.mapperJsonObject = mapperJsonObject;
    }

    public List<ProductoDomain> verifyContent(Object response, String requestId) {
        if (response != null) {
            ResponseProductoDomain responseDomain = convertResponse(response);
            if (Objects.equals(responseDomain.getId().toString(), requestId)) {
                if (responseDomain.getStateResponse() == StateResponse.ERROR) {
                    throw ServiceCustomException.createUserException(responseDomain.getMessage());
                }
                return responseDomain.getData();
            }
        }
        return null;
    }

    private ResponseProductoDomain convertResponse(Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Optional<String> response = mapperJsonObject.execute(object);
            String base64 = response.get().replace("\"", "");
            byte[] originalBytes = Base64.getDecoder().decode(base64);
            return objectMapper.readValue(originalBytes, ResponseProductoDomain.class);
        } catch (JsonMappingException exception) {
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrio un error mappeando el json");
        } catch (IOException exception) {
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrio un error procesando el json");
        } catch (Exception exception) {
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrio un error inesperado convirtiendo el objeto");
        }
    }


}
