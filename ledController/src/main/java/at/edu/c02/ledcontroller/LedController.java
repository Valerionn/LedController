package at.edu.c02.ledcontroller;

import org.json.JSONObject;

import java.io.IOException;

public interface LedController {
    void demo() throws IOException;

    JSONObject[] getGroupLeds() throws IOException;
    JSONObject setColorState(String light) throws IOException;
}
