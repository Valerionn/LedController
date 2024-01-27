package at.edu.c02.ledcontroller;

import org.json.JSONObject;

import java.io.IOException;

public interface ApiService {
    JSONObject getLights() throws IOException;

    JSONObject getLight(String id) throws IOException;

    void setLight(String id, String color, boolean on) throws IOException;

}
