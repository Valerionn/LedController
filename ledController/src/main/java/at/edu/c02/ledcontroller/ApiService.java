package at.edu.c02.ledcontroller;

import org.json.JSONObject;

import java.io.IOException;

public interface ApiService {
    JSONObject getLights() throws IOException;

    // NEU: Einzelne LED per ID abfragen
    JSONObject getLight(int id) throws IOException;
}
