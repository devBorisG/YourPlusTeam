package com.uco.yourplus.serviceyourplus.usecase.microservices.procesador.response;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.crosscuttingyourplus.helper.json.MapperJsonObject;
import com.uco.yourplus.serviceyourplus.domain.ResponseDomain;
import com.uco.yourplus.serviceyourplus.domain.enumeration.StateResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class ProcessRabbitResponse<T> {

    private final MapperJsonObject mapperJsonObject;

    public ProcessRabbitResponse(MapperJsonObject mapperJsonObject) {
        this.mapperJsonObject = mapperJsonObject;
    }

    public List<T> verifyContent(Object response, String requestId) {
        if (response != null) {
            ResponseDomain<T> responseDomain = convertResponse(response);
            if (Objects.equals(responseDomain.getId().toString(), requestId)) {
                if (responseDomain.getStateResponse() == StateResponse.ERROR) {
                    throw ServiceCustomException.createUserException(responseDomain.getMessage());
                }
                return responseDomain.getData();
            }
        }
        return null;
    }

    private ResponseDomain<T> convertResponse(Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Optional<String> response = mapperJsonObject.execute(object);
            String base64 = response.get().replace("\"", "");
            byte[] originalBytes = Base64.getDecoder().decode(base64);
            TypeReference<ResponseDomain<T>> typeReference = new TypeReference<>() {
            };
            return objectMapper.readValue(originalBytes, typeReference);
        } catch (JsonMappingException exception) {
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrio un error mappeando el json");
        } catch (IOException exception) {
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrio un error procesando el json");
        } catch (Exception exception) {
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrio un error inesperado convirtiendo el objeto");
        }
    }

}
