package com.uco.yourplus.serviceyourplus.domain.response;

import com.uco.yourplus.crosscuttingyourplus.helper.ObjectHelper;
import com.uco.yourplus.crosscuttingyourplus.helper.StringHelper;
import com.uco.yourplus.serviceyourplus.domain.ProductoDomain;
import com.uco.yourplus.serviceyourplus.domain.enumeration.StateResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ResponseProductoDomain {
    private UUID id;
    private StateResponse stateResponse;
    private String message;

    private List<ProductoDomain> data;

    public ResponseProductoDomain() {
        super();
        setId(UUID.randomUUID());
        setData(new ArrayList<>());
        setMessage(StringHelper.EMPTY);
        setStateResponse(StateResponse.ERROR);
    }

    public ResponseProductoDomain(UUID id, StateResponse stateResponse, String message, List<ProductoDomain> data) {
        super();
        setId(id);
        setData(data);
        setMessage(message);
        setStateResponse(stateResponse);
    }

    public StateResponse getStateResponse() {
        return stateResponse;
    }

    public void setStateResponse(StateResponse stateResponse) {
        this.stateResponse = ObjectHelper.getDefaultIfNull(stateResponse, StateResponse.ERROR);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = ObjectHelper.getDefaultIfNull(message, StringHelper.EMPTY);
    }

    public List<ProductoDomain> getData() {
        return data;
    }

    public void setData(List<ProductoDomain> data) {
        this.data = ObjectHelper.getDefaultIfNull(data, new ArrayList<>());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
