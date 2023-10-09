package com.uco.yourplus.serviceyourplus.response;


public interface HandlerReceiveMessage {

    boolean waitForResponse(String requestId);
}
