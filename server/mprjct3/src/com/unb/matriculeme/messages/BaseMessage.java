package com.unb.matriculeme.messages;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class BaseMessage implements MessageInterface {

    private int Code;

    private String Message;

    public BaseMessage(int code, String message) {
        this.Code = code;
        this.Message = message;
    }

    private BaseMessage() throws NotImplementedException {
        //Not Allowed Here
    }

    public void SetMessage(String message) {
        this.Message = message;
    }

    public void SetCode(int code) {
        this.Code = code;
    }

    public int GetCode() {
        return this.Code;
    }

    public String GetMessage() {
        return this.Message;
    }

    @Override
    public String RenderMessage() {
        GsonBuilder builder = new GsonBuilder();
        builder.disableHtmlEscaping();
        Gson gson = builder.create();
        return gson.toJson(this);
    }
}
