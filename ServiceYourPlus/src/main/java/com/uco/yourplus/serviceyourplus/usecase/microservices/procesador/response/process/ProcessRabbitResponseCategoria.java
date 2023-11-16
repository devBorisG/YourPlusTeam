package com.uco.yourplus.serviceyourplus.usecase.microservices.procesador.response.process;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.crosscuttingyourplus.helper.json.MapperJsonObject;
import com.uco.yourplus.serviceyourplus.domain.CategoriaDomain;
import com.uco.yourplus.serviceyourplus.domain.enumeration.StateResponse;
import com.uco.yourplus.serviceyourplus.domain.response.ResponseCategoriaDomain;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class ProcessRabbitResponseCategoria {

    private final MapperJsonObject mapperJsonObject;


    public ProcessRabbitResponseCategoria(MapperJsonObject mapperJsonObject) {
        this.mapperJsonObject = mapperJsonObject;
    }

    public List<CategoriaDomain> verifyContent(Object response, String requestId){
        if(response != null){
            ResponseCategoriaDomain responseCategoriaDomain = convertResponse(response);
            if(Objects.equals(responseCategoriaDomain.getId().toString(),requestId)){
                if(responseCategoriaDomain.getStateResponse() == StateResponse.ERROR){
                    throw ServiceCustomException.createUserException(responseCategoriaDomain.getMessage());
                }
                return responseCategoriaDomain.getData();
            }
        }
        return null;
    }

    private ResponseCategoriaDomain convertResponse(Object object){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            Optional<String> response = mapperJsonObject.execute(object);
            String base64 = response.get().replace("\"","");
            byte[] originalBytes = Base64.getDecoder().decode(base64);
            return objectMapper.readValue(originalBytes,ResponseCategoriaDomain.class);
        }catch(JsonMappingException exception){
            throw ServiceCustomException.createTechnicalException(exception,"Ocurrio un error mapeando el json");
        } catch(IOException exception){
            throw ServiceCustomException.createTechnicalException(exception,"Ocurrio un error procesando el json");
        } catch(Exception exception){
            throw ServiceCustomException.createTechnicalException(exception,"Ocurrio un error inesperado convirtiendo el objeto");
        }
    }
}
