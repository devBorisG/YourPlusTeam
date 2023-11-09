package com.uco.yourplus.serviceyourplus.usecase.microservices.procesador.response.process;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.crosscuttingyourplus.helper.json.MapperJsonObject;
import com.uco.yourplus.serviceyourplus.domain.LaboratorioDomain;
import com.uco.yourplus.serviceyourplus.domain.enumeration.StateResponse;
import com.uco.yourplus.serviceyourplus.domain.response.ResponseLaboratorioDomain;
import com.uco.yourplus.serviceyourplus.domain.response.ResponseProductoDomain;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class ProcessRabbitResponseLaboratorio {

    private final MapperJsonObject mapperJsonObject;


    public ProcessRabbitResponseLaboratorio(MapperJsonObject mapperJsonObject) {
        this.mapperJsonObject = mapperJsonObject;
    }

    public List<LaboratorioDomain> verifyContent(Object response, String requestId){
        if(response != null){
            ResponseLaboratorioDomain responseLaboratorioDomain = convertResponse(response);
            if(Objects.equals(responseLaboratorioDomain.getId().toString(),requestId)){
                if(responseLaboratorioDomain.getStateResponse() == StateResponse.ERROR){
                    throw ServiceCustomException.createUserException(responseLaboratorioDomain.getMessage());
                }
                return responseLaboratorioDomain.getData();
            }
        }
        return null;
    }

    private ResponseLaboratorioDomain convertResponse(Object object){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            Optional<String> response = new mapperJsonObject.execute(object);
            String base64 = response.get().replace("\"", "");
            byte[] originalBytes = base64.getDecoder().decode(base64);
            return objectMapper.readValue(originalBytes, ResponseProductoDomain.class);
        } catch(JsonMappingException exception){
            throw ServiceCustomException.createTechnicalException(exception,"Ocurrio un error mapeando el json");
        } catch(IOException exception){
            throw ServiceCustomException.createTechnicalException(exception,"Ocurrio un error procesando el json");
        } catch(Exception exception){
            throw ServiceCustomException.createTechnicalException(exception,"Ocurrio un error inesperado convirtiendo el objeto");
        }
    }
}
