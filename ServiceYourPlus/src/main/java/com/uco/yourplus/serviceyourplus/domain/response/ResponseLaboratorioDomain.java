package com.uco.yourplus.serviceyourplus.domain.response;

import com.uco.yourplus.crosscuttingyourplus.helper.StringHelper;
import com.uco.yourplus.serviceyourplus.domain.LaboratorioDomain;
import com.uco.yourplus.serviceyourplus.domain.enumeration.StateResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ResponseLaboratorioDomain {

    private UUID id;
    private StateResponse stateResponse;
    private String message;
    private List<LaboratorioDomain> data;

    public ResponseLaboratorioDomain(){
        super();
        setId(UUID.randomUUID());
        setData(new ArrayList<>());
        setMessage(StringHelper.EMPTY);
        setStateResponse(StateResponse.ERROR);
    }

    public ResponseLaboratorioDomain(UUID id, StateResponse stateResponse, String message, List<LaboratorioDomain> data){
        super();
        setId(id);
        setData(data);
        setMessage(message);
        setStateResponse(stateResponse);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public StateResponse getStateResponse() {
        return stateResponse;
    }

    public void setStateResponse(StateResponse stateResponse) {
        this.stateResponse = stateResponse;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<LaboratorioDomain> getData() {
        return data;
    }

    public void setData(List<LaboratorioDomain> data) {
        this.data = data;
    }
}
