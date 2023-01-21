package at.edu.c02.ledcontroller;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * This class should handle all HTTP communication with the server.
 * Each method here should correspond to an API call, accept the correct parameters and return the response.
 * Do not implement any other logic here - the ApiService will be mocked to unit test the logic without needing a server.
 */
public class ApiServiceImpl implements ApiService {
    /**
     * This method calls the `GET /getLights` endpoint and returns the response.
     * TODO: When adding additional API calls, refactor this method. Extract/Create at least one private method that
     * handles the API call + JSON conversion (so that you do not have duplicate code across multiple API calls)
     *
     * @return `getLights` response JSON object
     * @throws IOException Throws if the request could not be completed successfully
     */
    @Override
    public JSONObject getLights() throws IOException
    {
        // Connect to the server
        URL url = new URL("https://balanced-civet-91.hasura.app/api/rest/getLights");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // and send a GET request
        connection.setRequestMethod("GET");
        connection.setRequestProperty("X-Hasura-Group-ID", "98fc1c149afbf4c899");
        // Read the response code
        int responseCode = connection.getResponseCode();
        if(responseCode != HttpURLConnection.HTTP_OK) {
            // Something went wrong with the request
            throw new IOException("Error: getLights request failed with response code " + responseCode);
        }

        // The request was successful, read the response
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        // Save the response in this StringBuilder
        StringBuilder sb = new StringBuilder();

        int character;
        // Read the response, character by character. The response ends when we read -1.
        while((character = reader.read()) != -1) {
            sb.append((char) character);
        }

        String jsonText = sb.toString();
        // Convert response into a json object
        return new JSONObject(jsonText);
    }

    @Override
    public JSONObject getLight(int id) throws IOException {
        JSONArray allLights = getLights().getJSONArray("lights");

        for (int i = 0; i < allLights.length(); i++) {

            JSONObject currentLight = allLights.getJSONObject(i);

            if (currentLight.getInt("id") == id){
                return currentLight;
            }

        }

        return null;
    }

    @Override
    public JSONObject setLight(int id, String color, Boolean on) throws IOException {


            JSONObject light = new JSONObject();
            light.put("id", id);
            light.put("color", color);
            light.put("state", on);
            return POSTRequest(new URL("https://balanced-civet-91.hasura.app/api/rest/setLight"), light.toString());


    }


    public JSONObject POSTRequest(URL url, String light) throws IOException {


        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // and send a POST request
        connection.setRequestMethod("PUT");

        connection.setRequestProperty("X-Hasura-Group-ID", "98fc1c149afbf4c899");
        //set Request content
        connection.setRequestProperty("Content-Type", "application/json");
        //set response format
        connection.setRequestProperty("Accept", "application/json");
        //Ensure the Connection Will Be Used to Send Content
        connection.setDoOutput(true);

        //Create the Request Body

        try(OutputStream os = connection.getOutputStream()) {
            byte[] input = light.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        //Read the Response From Input Stream
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
            return new JSONObject(response.toString());
        }



    }


}
