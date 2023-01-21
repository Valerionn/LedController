package at.edu.c02.ledcontroller;

import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

public class EndToEndTests {

    @Test
    public void testSetLight() throws IOException {

        ApiServiceImpl apiService = new ApiServiceImpl();
        JSONObject object = apiService.setLight(34,"#f00",true);
        JSONObject newObject = apiService.getLight(34);

    }
}
