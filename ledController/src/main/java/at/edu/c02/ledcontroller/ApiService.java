package at.edu.c02.ledcontroller;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public interface ApiService {
    JSONObject getLights() throws IOException;
    JSONObject getLight(int ID) throws IOException;
    //JSONArray getGroupLeds(String groupName) throws IOException;
}
