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
}
