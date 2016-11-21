package manager;

import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class DataMining {

    private static String serverAddress = "http://172.16.5.95:8081";
    private static String historyPath = "/DataMining/rest/datamining/historico";

    public static boolean RequestHistory(String data) {
        try {
            //@TODO Remove this thing
            WebTarget target = ClientBuilder.newClient(new ClientConfig()).target(getHistoryModule());

            Response response = target.request("application/textplain").post(Entity.entity(data, "application/textplain"), Response.class); // What to do here

            //@TODO This shouldn't exist.
            //String jsonLine = response.readEntity(String.class);
            //System.out.println(jsonLine);

            return response.getStatus() == 200;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static String getServerAddress() {
        return serverAddress;
    }

    public static String getHistoryPath() {
        return historyPath;
    }

    public static String getHistoryModule() {
        return serverAddress + historyPath;
    }
}
