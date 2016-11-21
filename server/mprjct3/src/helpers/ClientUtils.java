package helpers;

import com.google.gson.Gson;
import messages.MessageInterface;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ClientUtils {

    public static Response SendResponse(String message, String type) {
        return Response.ok(message, type).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS").build();
    }

    public static Response SendEmptyResponse() {
        return SendResponse("{}", MediaType.APPLICATION_JSON);
    }

    public static Response SendMessage(MessageInterface message) {
        return SendResponse(message.RenderMessage(), MediaType.APPLICATION_JSON);
    }

    public static Response SendResponse(Object message, String type) {
        return SendResponse(new Gson().toJson(message), type);
    }
}
