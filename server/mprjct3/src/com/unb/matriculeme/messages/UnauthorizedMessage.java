package com.unb.matriculeme.messages;

public class UnauthorizedMessage extends BaseMessage {

    public UnauthorizedMessage() {
        this.SetCode(403);
        this.SetMessage("You don't have authorization to see this data!");
    }

    public UnauthorizedMessage(String customNotFound) {
        this.SetCode(403);
        this.SetMessage(customNotFound);
    }
}
