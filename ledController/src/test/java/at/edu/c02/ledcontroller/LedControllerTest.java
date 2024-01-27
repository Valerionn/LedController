package at.edu.c02.ledcontroller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class LedControllerTest {
    /**
     * This test is just here to check if tests get executed. Feel free to delete it when adding your own tests.
     * Take a look at the stack calculator tests again if you are unsure where to start.
     */
    private JSONObject readJsonFile (String filename) throws IOException {
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);
        return new JSONObject(br.readLine());
        //return new JSONObject("{\"lights\":[{\"id\":20,\"color\":\"#fff\",\"on\":false,\"groupByGroup\":{\"name\":\"C\"}}, {\"id\":21,\"color\":\"#fff\",\"on\":false,\"groupByGroup\":{\"name\":\"C\"}}, {\"id\":22,\"color\":\"#fff\",\"on\":false,\"groupByGroup\":{\"name\":\"C\"}}, {\"id\":23,\"color\":\"#fff\",\"on\":false,\"groupByGroup\":{\"name\":\"C\"}}, {\"id\":24,\"color\":\"#fff\",\"on\":false,\"groupByGroup\":{\"name\":\"C\"}}, {\"id\":25,\"color\":\"#fff\",\"on\":false,\"groupByGroup\":{\"name\":\"C\"}}, {\"id\":26,\"color\":\"#fff\",\"on\":false,\"groupByGroup\":{\"name\":\"C\"}}, {\"id\":27,\"color\":\"#fff\",\"on\":false,\"groupByGroup\":{\"name\":\"C\"}}]}\n");
    }
    @Test
    public void dummyTest() {
        assertEquals(1, 1);
    }

    @Test
    public void testGetGroupLeds() throws IOException {
       ApiService apiService = mock(ApiService.class);
       LedController ledController = new LedControllerImpl(apiService);



       /*
        JSONArray jsonArray = new JSONArray();
        String currJSONString = "";

        while( (currJSONString = br.readLine()) != null ){
            JSONObject currentObject = new JSONObject(currJSONString);
            jsonArray.put(currentObject);
        }
        */

       when(apiService.getLights()).thenReturn(readJsonFile("src/test/resources/getLights.json"));
        JSONObject groupObject = readJsonFile("src/test/resources/groupByGroup.json");
        JSONArray group = groupObject.getJSONArray("lights");
       assertEquals(ledController.getGroupLeds().length(),group.length());
       assertEquals(ledController.getGroupLeds().get(0).toString(),group.get(0).toString());
        // when(cal.perfom(Operation.add)).thenReturn(3.0);

    }
}
