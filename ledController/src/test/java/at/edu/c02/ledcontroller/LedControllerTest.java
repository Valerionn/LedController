package at.edu.c02.ledcontroller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class LedControllerTest {

    @Test
    public void getGroupLedsUsesApiServiceAndFiltersToGroupedLights() throws Exception {
        ApiService apiService = mock(ApiService.class);
        LedController controller = new LedControllerImpl(apiService);

        JSONObject groupLightA = new JSONObject()
                .put("id", 1)
                .put("color", "#fff")
                .put("on", true)
                .put("groupByGroup", new JSONObject().put("name", "A"));

        JSONObject groupLightB = new JSONObject()
                .put("id", 2)
                .put("color", "#000")
                .put("on", false)
                .put("groupByGroup", new JSONObject().put("name", "B"));

        JSONObject withoutGroup = new JSONObject()
                .put("id", 3)
                .put("color", "#123456")
                .put("on", true);

        JSONObject nullGroup = new JSONObject()
                .put("id", 4)
                .put("color", "#abcdef")
                .put("on", false)
                .put("groupByGroup", JSONObject.NULL);

        JSONArray lights = new JSONArray()
                .put(groupLightA)
                .put(withoutGroup)
                .put(groupLightB)
                .put(nullGroup);

        when(apiService.getLights()).thenReturn(new JSONObject().put("lights", lights));

        JSONArray result = controller.getGroupLeds();

        JSONArray expected = new JSONArray()
                .put(groupLightA)
                .put(groupLightB);

        assertEquals(expected.toString(), result.toString());
        verify(apiService, times(1)).getLights();
        verify(apiService, never()).getLight(anyInt());
    }

    @Test
    public void getLightReturnsSingleLightFromApi() throws Exception {
        ApiService apiService = mock(ApiService.class);
        LedController controller = new LedControllerImpl(apiService);

        JSONObject light = new JSONObject()
                .put("id", 10)
                .put("color", "#0f0")
                .put("on", true);

        when(apiService.getLight(10)).thenReturn(new JSONObject()
                .put("lights", new JSONArray().put(light)));

        JSONObject result = controller.getLight(10);

        assertEquals(light.toString(), result.toString());
        verify(apiService, times(1)).getLight(10);
        verify(apiService, never()).getLights();
    }

    @Test
    public void turnOffAllLedsCallsSetLightForConfiguredIds() throws Exception {
        ApiService apiService = mock(ApiService.class);
        LedController controller = new LedControllerImpl(apiService);

        when(apiService.setLight(anyInt(), anyString(), anyBoolean())).thenReturn(new JSONObject());

        controller.turnOffAllLeds();

        verify(apiService).setLight(20, "#000000", false);
        verify(apiService).setLight(21, "#000000", false);
        verify(apiService).setLight(22, "#000000", false);
        verify(apiService).setLight(23, "#000000", false);
        verify(apiService).setLight(24, "#000000", false);
        verify(apiService).setLight(25, "#000000", false);
        verify(apiService).setLight(26, "#000000", false);
        verify(apiService).setLight(27, "#000000", false);
        verify(apiService, times(8)).setLight(anyInt(), eq("#000000"), eq(false));
        verifyNoMoreInteractions(apiService);
    }
}
