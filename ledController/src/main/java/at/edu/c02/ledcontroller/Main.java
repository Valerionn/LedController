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
            System.out.println("Enter 'exit' to exit the program");
            input = reader.readLine();
            if(input.equalsIgnoreCase("demo"))
            {
                ledController.demo();
            }
            if(input.equalsIgnoreCase("groupstatus"))
            {
                ledController.getGroupStatus();
            }
            if(input.equalsIgnoreCase("status"))
            {
                System.out.println("Please specify LED ID:");
                input = reader.readLine();
                ledController.status(Integer.parseInt(input));
            }
            if(input.equalsIgnoreCase("setled"))
            {
                System.out.println("Which LED?");
                int id = Integer.parseInt(reader.readLine());
                System.out.println("Which color?");
                String color = reader.readLine();
                ledController.setLightOnController(id,color);
                System.out.println("LED color set!");
            }



        }
    }
}
