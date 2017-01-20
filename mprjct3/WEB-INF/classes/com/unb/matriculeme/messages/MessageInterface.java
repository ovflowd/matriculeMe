package com.unb.matriculeme.messages;

public interface MessageInterface {

    int GetCode();

    String GetMessage();

    void SetMessage(String message);

    void SetCode(int code);

    String RenderMessage();
}
