package com.uco.yourplus.serviceyourplus.usecase.microservices;

import java.util.List;

public interface HandlerReceiveMessageList<T> {
    List<T> waitForResponse(String requestId);
}
