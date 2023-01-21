package at.edu.c02.ledcontroller;

import org.json.JSONArray;
import org.json.JSONObject;

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
            System.out.println("Enter 'groupstatus' to send a groupstatus request");
            System.out.println("Enter 'status' to send a status request");
            System.out.println("Enter 'exit' to exit the program");
            input = reader.readLine();
            if(input.equalsIgnoreCase("demo"))
            {
                ledController.demo();
            }
            if(input.equalsIgnoreCase("groupstatus")){
                JSONArray temp = ledController.getGroupLEds();
                for(int i = 0; i < temp.length(); i++){
                    System.out.println("LED " + temp.getJSONObject(i).getInt("id") + " currently " + temp.getJSONObject(i).getBoolean("on") + ". Color: "+ temp.getJSONObject(i).getString("color") + ".");
                }
        }
            if(input.equalsIgnoreCase("status")){
                System.out.println("Plaese specify LED ID :");
                String id = reader.readLine();
                int ergebnis = Integer.parseInt(id);
                System.out.println("LED " + id + " is currently " + temp2.getBoolean("on") + " color: " + temp2.getString("color"));
            }
        }
    }
}
