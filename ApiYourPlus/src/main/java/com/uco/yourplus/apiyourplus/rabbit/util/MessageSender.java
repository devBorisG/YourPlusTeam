package com.uco.yourplus.apiyourplus.rabbit.util;

public interface MessageSender<T> {
    void execute(T message, String idMessage);
}
