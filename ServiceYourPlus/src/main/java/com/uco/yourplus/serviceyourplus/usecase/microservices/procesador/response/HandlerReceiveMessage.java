package com.uco.yourplus.serviceyourplus.usecase.microservices.procesador.response;


public interface HandlerReceiveMessage {

    boolean waitForResponse(String requestId);
}
