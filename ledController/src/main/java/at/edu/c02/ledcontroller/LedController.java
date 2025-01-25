package at.edu.c02.ledcontroller;

import java.io.IOException;

public interface LedController {
    void demo() throws IOException;
    void setLed(int led, String color,boolean status)throws IOException;
}
