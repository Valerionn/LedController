package at.edu.c02.ledcontroller;

import org.json.JSONArray;

import java.io.IOException;

public interface LedController {
    void demo() throws IOException;

    /**
     * Returns the statuses of all LEDs that are associated with a group.
     */
    JSONArray getGroupLeds() throws IOException;
}
