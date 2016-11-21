package com.unb.matriculeme.messages;

public class AllRightMessage extends BaseMessage {

    public AllRightMessage() {
        this.SetCode(200);
        this.SetMessage("Everything OK.");
    }

    public AllRightMessage(String customOkMessage) {
        this.SetCode(200);
        this.SetMessage(customOkMessage);
    }
}
