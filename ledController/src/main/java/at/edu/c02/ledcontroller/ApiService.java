package at.edu.c02.ledcontroller;

import org.json.JSONObject;

import java.io.IOException;

public interface ApiService {
    JSONObject communicate(String url) throws IOException;

    String setURL (String method);
    String setURL (String method, int id);
}
