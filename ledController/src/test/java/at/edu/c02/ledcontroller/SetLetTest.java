package at.edu.c02.ledcontroller;

import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class SetLetTest {
    /**
     * This test is just here to check if tests get executed. Feel free to delete it when adding your own tests.
     * Take a look at the stack calculator tests again if you are unsure where to start.
     */
    @Test
    public void dummyTest() {
        ApiService api = new ApiServiceImpl();
        JSONObject putJson;
        JSONObject response;
        try {
            putJson = api.setLed(57,"#F00",true);
            response = api.getLight(57);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertTrue(putJson.equals(response));


    }
}
