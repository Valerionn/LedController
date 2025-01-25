package at.edu.c02.ledcontroller;

import org.json.JSONObject;

import java.io.IOException;

public interface ApiService {
    JSONObject getLights() throws IOException;
    Boolean setLed(int led, String color,boolean status) throws IOException;
    public void turnAllOff() throws IOException;

    JSONObject getLight(int id) throws IOException;
}
