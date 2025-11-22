package at.edu.c02.ledcontroller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class ApiServiceImplTest {

    @Test
    public void getLightsDelegatesToSharedRequest() throws Exception {
        ApiServiceImpl apiService = spy(new ApiServiceImpl());
        JSONObject response = new JSONObject().put("lights", new JSONArray());

        doReturn(response).when(apiService).sendGetRequest("/getLights");

        JSONObject result = apiService.getLights();

        assertEquals(response.toString(), result.toString());
        verify(apiService, times(1)).sendGetRequest("/getLights");
    }

    @Test
    public void getLightUsesIdPathAndSharedRequest() throws Exception {
        ApiServiceImpl apiService = spy(new ApiServiceImpl());
        JSONObject response = new JSONObject().put("lights",
                new JSONArray().put(new JSONObject().put("id", 5).put("on", true)));

        doReturn(response).when(apiService).sendGetRequest("/lights/5");

        JSONObject result = apiService.getLight(5);

        assertEquals(response.toString(), result.toString());
        verify(apiService, times(1)).sendGetRequest("/lights/5");
    }

    @Test
    public void setLightUsesPutAndSendsBody() throws Exception {
        ApiServiceImpl apiService = spy(new ApiServiceImpl());
        JSONObject response = new JSONObject().put("update_lights_by_pk",
                new JSONObject().put("color", "#abc").put("on", true));

        doReturn(response).when(apiService).sendRequest(eq("/setLight"), eq("PUT"), any(JSONObject.class));

        JSONObject result = apiService.setLight(7, "#abc", true);

        assertEquals(response.toString(), result.toString());
        ArgumentCaptor<JSONObject> bodyCaptor = ArgumentCaptor.forClass(JSONObject.class);
        verify(apiService, times(1)).sendRequest(eq("/setLight"), eq("PUT"), bodyCaptor.capture());
        JSONObject sentBody = bodyCaptor.getValue();
        assertEquals(7, sentBody.getInt("id"));
        assertEquals("#abc", sentBody.getString("color"));
        assertTrue(sentBody.getBoolean("state"));
    }
}
