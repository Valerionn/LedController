package at.edu.c02.ledcontroller;

import org.json.JSONObject;
import org.json.JSONArray;

import java.io.*;
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

        String command = "https://balanced-civet-91.hasura.app/api/rest/getLights";
        String method = "GET";
        JSONObject result = initialize(command,method);
        return result;

    }

    @Override
    public JSONObject getLight(int id) throws IOException {

        String command = "https://balanced-civet-91.hasura.app/api/rest/lights/"+ id;
        String method = "GET";
        JSONObject result = initialize(command,method);
        return result;

    }

    public JSONObject initialize(String command, String method) throws  IOException{

        URL url = new URL(command);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod(method);
        connection.setRequestProperty("X-Hasura-Group-ID", getSecret(new File("C:\\Users\\mario\\IdeaProjects\\LedController\\secret.txt")));

        int responseCode = connection.getResponseCode();
        if(responseCode != HttpURLConnection.HTTP_OK) {
            // Something went wrong with the request
            throw new IOException("Error: request failed with response code " + responseCode);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        StringBuilder sb = new StringBuilder();

        int character;

        while((character = reader.read()) != -1) {
            sb.append((char) character);
        }

        String jsonText = sb.toString();
        // Convert response into a json object
        return new JSONObject(jsonText);

    }
    private static HttpURLConnection extracted(String setId, String request) throws IOException {
        URL url = new URL("https://balanced-civet-91.hasura.app/api/rest/" + setId);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(request);
        connection.setRequestProperty("H", "5f26cca3877ad");
        return connection;
    }
    @Override
    public JSONObject setColor(int id, String color) throws IOException {
        String setId = "setcolor";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("color", color);
        String response = "PUT";
        HttpURLConnection connection = extracted(setId, response);
        connection.setDoOutput(true);
        try(OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonObject.toString().getBytes("utf-8");
            os.write(input, 0, input.length);
        }
        JSONObject responseJson = null;
        if (connection.getResponseCode() == 200) {
            String responseMessage = connection.getResponseMessage();
            responseJson = getJsonObject(responseMessage);
        }
        return responseJson;

    }

    public String getSecret(File file)
    {
        try(BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            String input = reader.readLine();
            return input;

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    private static JSONObject getJsonObject(String hilfe) {
        JSONObject responseJson = new JSONObject();
        JSONArray jsonArray = new JSONArray(hilfe);
        for ( int i = 0; i<jsonArray.length(); i++){
           responseJson = jsonArray.getJSONObject(i);
        }
        return responseJson;
    }

    @Override
    public JSONObject setStatus(int id, boolean state) throws IOException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",id);
        jsonObject.put("state",state);

        String setId = "setstatus";
        String response = "PUT";
        HttpURLConnection connection = extracted(setId, response);
        connection.setDoOutput(true);
        try(OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonObject.toString().getBytes("utf-8");
            os.write(input, 0, input.length);
        }
        JSONObject responseJson = new JSONObject();
       if(connection.getResponseCode() == 200){
           String responseMessage = connection.getResponseMessage();
           responseJson = getJsonObject(responseMessage);


       }
       return responseJson;

    }
}
