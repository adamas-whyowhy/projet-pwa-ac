package pojo;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import jakarta.persistence.Entity;

@Entity
public class ModuleTelecomT1 extends ModuleTelecom {
	
	@Override
	public int envoyerMessage(String message) {
		HttpURLConnection con = null;
        int responseCode = 0;
        try {
            URL obj = new URL("http://localhost:8082/projet-pwa-ac/rapport");
            con = (HttpURLConnection) obj.openConnection();

            // Set request properties
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/xml");
            con.setDoOutput(true);

            // Send request
            OutputStream os = con.getOutputStream();
            os.write(message.getBytes());
            os.flush();
            os.close();

            // Get response
            responseCode = con.getResponseCode();
        } catch (Exception e) {
            // Handle exception
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
        return responseCode;
	}

}
