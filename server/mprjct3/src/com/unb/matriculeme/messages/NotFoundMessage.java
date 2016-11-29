package com.unb.matriculeme.messages;

public class NotFoundMessage extends BaseMessage {

    public NotFoundMessage() {
        this.SetCode(404);
        this.SetMessage("The desired element wasn't found on our database data.");
    }

    public NotFoundMessage(String customNotFound) {
        this.SetCode(404);
        this.SetMessage(customNotFound);
    }
}
