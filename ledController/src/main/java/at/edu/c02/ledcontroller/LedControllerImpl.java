package at.edu.c02.ledcontroller;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This class handles the actual logic
 */
public class LedControllerImpl implements LedController {
    private final ApiService apiService;

    public LedControllerImpl(ApiService apiService)
    {
        this.apiService = apiService;
    }

    public ArrayList<JSONObject> getGroupLeds() throws IOException {
        // Array aus allen LED-Status der Gruppen-LEDs zurückgeben
        JSONObject response = apiService.getLights();
        JSONArray lights = response.getJSONArray("lights");
        ArrayList<JSONObject> arrayList = new ArrayList<>();
        for (int i = 0; i < lights.length(); i++) {
            /*if (!lights.getJSONObject(i).getJSONObject("groupByGroup").getString("name").equals("D")) {
                lights.remove(i);
            }*/
            JSONObject oneLight = lights.getJSONObject(i);
            JSONObject group = oneLight.getJSONObject("groupByGroup");

            if (group.getString("name").equals("D")) {
                arrayList.add(oneLight);
            }
        }
        //System.out.println(arrayList);
        return arrayList;
    }

    @Override
    public void demo() throws IOException
    {
        // Call `getLights`, the response is a json object in the form `{ "lights": [ { ... }, { ... } ] }`
        JSONObject response = apiService.getLights();
        // get the "lights" array from the response
        JSONArray lights = response.getJSONArray("lights");
        // read the first json object of the lights array
        JSONObject firstLight = lights.getJSONObject(0);
        // read int and string properties of the light
        System.out.println("First light id is: " + firstLight.getInt("id"));
        System.out.println("First light color is: " + firstLight.getString("color"));
    }

    @Override
    public void getGroupStatus() throws IOException {
        ArrayList<JSONObject> list = getGroupLeds();
        for (JSONObject jsonObject : list) {
            String onOff = "";
            if (jsonObject.getBoolean("on")) onOff = "on";
            else onOff = "off";
            System.out.println("LED " + jsonObject.getInt("id")
                    + " is currently " + onOff
                    + ". Color: " + jsonObject.getString("color"));
        }
    }

    @Override
    public void status(int id) throws IOException{
        // Call `getLights`, the response is a json object in the form `{ "lights": [ { ... }, { ... } ] }`
        JSONObject response = apiService.getLight(id);
        String onOff = "";
        if (response.getBoolean("on")){
            onOff = "on";
        } else {
            onOff = "off";
        }
        System.out.println("LED " + response.getInt("id") + " is currently " + onOff + ". Color: " +response.getString("color"));
    }

    @Override

    public void setLightOnController(int id, String color) throws IOException {
        apiService.setLight(id,color,true);
    }


    public void turnOffAllLeads() throws IOException {
        ArrayList<JSONObject> list = getGroupLeds();
        for (JSONObject jsonObject : list) {
            if (jsonObject.getBoolean("on")) jsonObject.put("on", false);
        }
        System.out.println(list);
    }


}
