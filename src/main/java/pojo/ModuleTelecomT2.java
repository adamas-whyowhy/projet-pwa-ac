package pojo;

import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import jakarta.persistence.Entity;

@Entity
public class ModuleTelecomT2 extends ModuleTelecom {

	@Override
	public int envoyerMessage(String message) {
        HttpURLConnection con = null;
        int responseCode = 0;
        try {
            URL obj = new URL("http://localhost:8082/projet-pwa-ac/rapport");
            con = (HttpURLConnection) obj.openConnection();

            // Set request properties
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            // Create JSON message
            JSONObject jsonMessage = new JSONObject();
            jsonMessage.put("message", message);

            // Send POST request
            con.getOutputStream().write(jsonMessage.toString().getBytes());
            responseCode = con.getResponseCode();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
        return responseCode;
	}

}
