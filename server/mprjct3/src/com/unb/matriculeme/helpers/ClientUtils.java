package com.unb.matriculeme.helpers;

import com.google.gson.Gson;
import com.unb.matriculeme.messages.MessageInterface;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List; 

public class ClientUtils {

    public static Response sendResponse(String message, String type, int statusCode) {
        return Response.status(statusCode).entity(message).type(type).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS").allow("OPTIONS").build();
    }

    public static Response sendResponse(String message, String type) {
        return sendResponse(message, type, 200);
    }

    public static Response sendEmptyResponse() {
        return sendResponse("{}", MediaType.APPLICATION_JSON);
    }

    public static Response sendMessage(MessageInterface message) {
        return sendResponse(message.RenderMessage(), MediaType.APPLICATION_JSON, message.GetCode());
    }

    public static Response sendResponse(Object message) {
        return sendResponse(new Gson().toJson(message), MediaType.APPLICATION_JSON);
    }

    public static Response sendResponse(List message) {
        return sendResponse(new Gson().toJson(message), MediaType.APPLICATION_JSON);
    }

    public static Response sendResponse(Object message, String type) {
        return sendResponse(new Gson().toJson(message), type);
    }

    public static Response sendResponse(List message, String type) {
        return sendResponse(new Gson().toJson(message), type);
    }
}
