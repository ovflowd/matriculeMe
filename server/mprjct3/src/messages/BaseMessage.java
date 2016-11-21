package messages;

import com.google.gson.Gson;

class BaseMessage implements MessageInterface {

    private int Code;

    private String Message;

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
        return new Gson().toJson(this);
    }
}
