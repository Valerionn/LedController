package at.edu.c02.ledcontroller;

import java.io.IOException;

public interface LedController {
    void demo() throws IOException;
    void getGroupLeds() throws IOException;
    void getStatus(int id) throws IOException;
}
