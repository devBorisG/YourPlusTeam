package com.uco.yourplus.apiyourplus.controller.response;

import com.uco.yourplus.crosscuttingyourplus.exceptions.messages.Message;
import com.uco.yourplus.crosscuttingyourplus.helper.ObjectHelper;
import com.uco.yourplus.crosscuttingyourplus.helper.StringHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa la respuesta de un controlador en la aplicación.
 *
 * @param <T> El tipo de datos de los elementos de datos contenidos en la respuesta.
 */
public class Response<T> {
    private List<Message> messageList;
    private List<T> data;

    private String token;

    /**
     * Constructor predeterminado que inicializa una instancia de Response sin mensajes ni datos.
     */
    public Response() {
        setMessageList(new ArrayList<>());
        setData(data);
        setToken(token);
    }

    /**
     * Constructor que permite especificar mensajes y datos al crear una instancia de Response.
     *
     * @param messages La lista de mensajes a incluir en la respuesta.
     * @param data     La lista de datos a incluir en la respuesta.
     */
    public Response(List<Message> messages, List<T> data, String token) {
        super();
        setMessageList(messages);
        setData(data);
        setToken(token);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = ObjectHelper.getDefaultIfNull(token, StringHelper.EMPTY);
    }

    /**
     * Obtiene la lista de mensajes de la respuesta.
     *
     * @return La lista de mensajes de la respuesta.
     */
    public List<Message> getMessageList() {
        return messageList;
    }

    /**
     * Establece la lista de mensajes de la respuesta.
     *
     * @param messageList La lista de mensajes a establecer.
     */
    public void setMessageList(List<Message> messageList) {
        this.messageList = ObjectHelper.getDefaultIfNull(messageList, new ArrayList<>());
    }

    /**
     * Obtiene la lista de datos de la respuesta.
     *
     * @return La lista de datos de la respuesta.
     */
    public List<T> getData() {
        return data;
    }

    /**
     * Establece la lista de datos de la respuesta.
     *
     * @param data La lista de datos a establecer.
     */
    public void setData(List<T> data) {
        this.data = ObjectHelper.getDefaultIfNull(data, new ArrayList<>());
    }

    /**
     * Agrega un mensaje fatal a la lista de mensajes de la respuesta.
     *
     * @param content El contenido del mensaje fatal.
     */
    public void addFatalMessage(final String content) {
        getMessageList().add(Message.createFatalMessage(content));
    }

    /**
     * Agrega un mensaje de error a la lista de mensajes de la respuesta.
     *
     * @param content El contenido del mensaje de error.
     */
    public void addErrorMessage(final String content) {
        getMessageList().add(Message.createErrorMessage(content));
    }

    /**
     * Agrega un mensaje de advertencia a la lista de mensajes de la respuesta.
     *
     * @param content El contenido del mensaje de advertencia.
     */
    public void addWarningMessage(final String content) {
        getMessageList().add(Message.createWarningMessage(content));
    }

    /**
     * Agrega un mensaje informativo a la lista de mensajes de la respuesta.
     *
     * @param content El contenido del mensaje informativo.
     */
    public void addInfoMessage(final String content) {
        getMessageList().add(Message.createInfoMessage(content));
    }

    /**
     * Agrega un mensaje de éxito a la lista de mensajes de la respuesta.
     *
     * @param content El contenido del mensaje de éxito.
     */
    public void addSuccesMessage(final String content) {
        getMessageList().add(Message.createSuccesMessage(content));
    }
}
