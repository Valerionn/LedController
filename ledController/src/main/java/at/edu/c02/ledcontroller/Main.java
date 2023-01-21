package at.edu.c02.ledcontroller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    /**
     * This is the main program entry point. TODO: add new commands when implementing additional features.
     */
    public static void main(String[] args) throws IOException, InterruptedException
    {
        LedController ledController = new LedControllerImpl(new ApiServiceImpl());

        String input = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(!input.equalsIgnoreCase("exit"))
        {
            System.out.println("=== LED Controller ===");
            System.out.println("Enter 'demo' to send a demo request");
            System.out.println("Enter 'groupstatus' to get status of LEDs");
            System.out.println("Enter 'status' to get status of one LED");
            System.out.println("Enter 'setled' to set the color for one LED");
            System.out.println("Enter 'spinningled' to spin the leds around the clock");
            System.out.println("Enter 'turnoffall' to turn of all leds");
            System.out.println("Enter 'exit' to exit the program");
            input = reader.readLine();

            if(input.equalsIgnoreCase("demo"))
            {
                ledController.demo();
            }

            if(input.equalsIgnoreCase("spinningled"))
            {
                String color;
                int nrTurns;

                System.out.println("How many turns?");
                nrTurns = Integer.parseInt(reader.readLine());

                System.out.println("Which color?");
                color = reader.readLine();

                System.out.println("Starting spinning led effect....");
                ledController.lauflicht(color, nrTurns);
            }

            if(input.equalsIgnoreCase("turnoffall"))
                ledController.turnOffAllLeds();

            if (input.equalsIgnoreCase("groupstatus"))
            {
                ArrayList<String> states = new ArrayList<>();
                states = ledController.getGroupLed(null);

                for (String state : states)
                {
                    System.out.println(state);
                }
            }

            if (input.equalsIgnoreCase("status"))
            {
                int id;

                System.out.println("Please specify LED ID:");
                id = Integer.parseInt(reader.readLine());

                System.out.println(ledController.getGroupLed(id));
            }

            if (input.equalsIgnoreCase("setled"))
            {
                int id;
                String color;

                System.out.println("Which LED?");
                id = Integer.parseInt(reader.readLine());

                System.out.println("Which color?");
                color = reader.readLine();

                ledController.setLedColor(id,color);
            }
        }
    }
}
