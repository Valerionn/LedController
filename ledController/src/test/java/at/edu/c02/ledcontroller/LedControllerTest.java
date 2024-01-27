package at.edu.c02.ledcontroller;

import org.json.JSONObject;
import org.junit.Test;

import java.io.BufferedReader;
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
    @Test
    public void dummyTest() {
        assertEquals(1, 1);
    }

    @Test
    public void testGetGroupLeds() throws IOException {
       ApiService apiService = mock(ApiService.class);
       LedController ledController = new LedControllerImpl(apiService);

       FileReader fr = new FileReader("getLights.json");
       BufferedReader br = new BufferedReader(fr);

       JSONObject jObject = new JSONObject(br);

       /*
        JSONArray jsonArray = new JSONArray();
        String currJSONString = "";

        while( (currJSONString = br.readLine()) != null ){
            JSONObject currentObject = new JSONObject(currJSONString);
            jsonArray.put(currentObject);
        }
        */

       when(apiService.getLights()).thenReturn(jObject);

        // when(cal.perfom(Operation.add)).thenReturn(3.0);

    }
}
