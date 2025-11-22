package at.edu.c02.ledcontroller;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiServiceImpl implements ApiService {

    private static final String BASE_URL = "https://balanced-civet-91.hasura.app/api/rest";
    private static final String GROUP_ID = "Todo"; // wird in Story 3.1 ersetzt

    // Hilfsmethode für GET-Requests
    JSONObject sendGetRequest(String path) throws IOException {
        URL url = new URL(BASE_URL + path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("X-Hasura-Group-ID", GROUP_ID);

        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new IOException("Error: GET " + path + " failed with code " + responseCode);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder sb = new StringBuilder();
        int character;
        while ((character = reader.read()) != -1) {
            sb.append((char) character);
        }

        return new JSONObject(sb.toString());
    }

    @Override
    public JSONObject getLights() throws IOException {
        return sendGetRequest("/getLights");
    }

    @Override
    public JSONObject getLight(int id) throws IOException {
        return sendGetRequest("/lights/" + id);
    }
}
