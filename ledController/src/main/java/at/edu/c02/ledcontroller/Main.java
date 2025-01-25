package at.edu.c02.ledcontroller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    /**
     * This is the main program entry point. TODO: add new commands when implementing additional features.
     */
    public static void main(String[] args) throws IOException {
        LedController ledController = new LedControllerImpl(new ApiServiceImpl());

        String input = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(!input.equalsIgnoreCase("exit"))
        {
            System.out.println("=== LED Controller ===");
            System.out.println("Enter 'demo' to send a demo request");
            System.out.println("Enter 'groupstatus' to show led state");
            System.out.println("Enter 'status' to show led state for specific led");
            System.out.println("Enter 'exit' to exit the program");
            System.out.println("Enter 'on' to exit the program");
            System.out.println("Enter 'alloff' to exit the program");
            input = reader.readLine();
            if(input.equalsIgnoreCase("demo"))
            {
                ledController.demo();
            }
            else if(input.equalsIgnoreCase("on"))
            {
                System.out.println("Please specify LED ID:");
                input = reader.readLine();
                int led = Integer.parseInt(input);
                ledController.setLed(led, "#0f0", true);
            }
            else if(input.equalsIgnoreCase("alloff"))
            {
                ledController.turnAllOff();
            }
             else if (input.equalsIgnoreCase("groupstatus")) {
                ledController.getGroupLeds();
            }
            else if (input.equalsIgnoreCase("status")) {
                System.out.println("Please specify LED ID:");
                input = reader.readLine();
                ledController.getStatus(Integer.parseInt(input));
            }
        }
    }
}
