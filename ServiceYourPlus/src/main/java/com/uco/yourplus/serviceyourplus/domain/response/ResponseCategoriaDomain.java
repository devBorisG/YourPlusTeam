package com.uco.yourplus.serviceyourplus.domain.response;

import com.uco.yourplus.crosscuttingyourplus.helper.StringHelper;
import com.uco.yourplus.serviceyourplus.domain.CategoriaDomain;
import com.uco.yourplus.serviceyourplus.domain.enumeration.StateResponse;

import javax.swing.plaf.nimbus.State;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ResponseCategoriaDomain {

    private UUID id;
    private StateResponse stateResponse;
    private String message;
    private List<CategoriaDomain> data;

    public ResponseCategoriaDomain(){
        super();
        setId(UUID.randomUUID());
        setData(new ArrayList<>());
        setMessage(StringHelper.EMPTY);
        setStateResponse(StateResponse.ERROR);
    }

    public ResponseCategoriaDomain(UUID id, StateResponse stateResponse, String message,List<CategoriaDomain> data){
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

    public List<CategoriaDomain> getData() {
        return data;
    }

    public void setData(List<CategoriaDomain> data) {
        this.data = data;
    }
}
