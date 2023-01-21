package at.edu.c02.ledcontroller;

import java.io.IOException;

public interface LedController {
    void demo() throws IOException;
    void getGroupStatus() throws IOException;
    void status(int id) throws IOException;

    void setLightOnController(int id, String color) throws IOException;

    void turnOffAllLeads() throws IOException;

}
