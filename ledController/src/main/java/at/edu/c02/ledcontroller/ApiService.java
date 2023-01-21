package at.edu.c02.ledcontroller;

import org.json.JSONObject;

import java.io.IOException;

public interface ApiService {
    JSONObject getLights() throws IOException;

    JSONObject getLight(int id) throws IOException;

    JSONObject setColor(int id, String color) throws IOException;

    JSONObject setStatus(int id, boolean state) throws IOException;
}
