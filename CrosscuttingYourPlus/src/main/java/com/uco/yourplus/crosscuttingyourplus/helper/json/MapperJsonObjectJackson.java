package com.uco.yourplus.crosscuttingyourplus.helper.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
public class MapperJsonObjectJackson implements MapperJsonObject{
    @Override
    public Optional<String> execute(Object object) {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            return Optional.ofNullable(objectMapper.writeValueAsString(object));
        } catch (JsonProcessingException exception) {
            return Optional.empty();
        }
    }

    @Override
    public <T> Optional<T> execute(String json, Class<T> targetClass) {
        try{
            ObjectMapper objectMapper = new ObjectMapper();

            return Optional.ofNullable(objectMapper.readValue(json, targetClass));
        } catch (Exception exception){
            return Optional.empty();
        }
    }

    @Override
    public Optional<String> executeGson(Object object) {
        try{
            Gson gson = new GsonBuilder().serializeNulls()
                    .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, typeOfT, context) -> LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                    .create();
            String objectStr = gson.toJson(object);
            return Optional.ofNullable(objectStr);
        }catch (Exception exception){
            return Optional.empty();
        }
    }
}
