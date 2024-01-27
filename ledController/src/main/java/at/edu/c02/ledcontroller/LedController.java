package at.edu.c02.ledcontroller;

import java.io.IOException;

public interface LedController {
    void demo() throws IOException;

    void getLights() throws IOException;
    void getLight(int id) throws IOException;
}
