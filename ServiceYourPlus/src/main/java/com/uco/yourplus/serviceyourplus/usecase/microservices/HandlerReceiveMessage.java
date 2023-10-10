package com.uco.yourplus.serviceyourplus.usecase.microservices;


public interface HandlerReceiveMessage {

    void waitForResponse(String requestId);
}
