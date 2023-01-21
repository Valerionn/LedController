package at.edu.c02.ledcontroller;

import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


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
    public void testGroupLeds1 () throws IOException {
        ApiService apiService = mock(ApiService.class);
        LedController leds = new LedControllerImpl(apiService);

        when(apiService.getLights()).thenReturn(new JSONObject("{\"lights\":[{\"color\":\"#000\",\"id\":57,\"groupByGroup\":{\"name\":\"H\"},\"on\":false},{\"color\":\"#000\",\"id\":58,\"groupByGroup\":{\"name\":\"H\"},\"on\":false},{\"color\":\"#000\",\"id\":59,\"groupByGroup\":{\"name\":\"H\"},\"on\":false},{\"color\":\"#000\",\"id\":60,\"groupByGroup\":{\"name\":\"H\"},\"on\":false},{\"color\":\"#000\",\"id\":61,\"groupByGroup\":{\"name\":\"H\"},\"on\":false},{\"color\":\"#fff\",\"id\":11,\"groupByGroup\":{\"name\":\"B\"},\"on\":true},{\"color\":\"#fff\",\"id\":12,\"groupByGroup\":{\"name\":\"B\"},\"on\":true},{\"color\":\"#fff\",\"id\":13,\"groupByGroup\":{\"name\":\"B\"},\"on\":true},{\"color\":\"#fff\",\"id\":14,\"groupByGroup\":{\"name\":\"B\"},\"on\":true},{\"color\":\"#fff\",\"id\":15,\"groupByGroup\":{\"name\":\"B\"},\"on\":true},{\"color\":\"#fff\",\"id\":16,\"groupByGroup\":{\"name\":\"B\"},\"on\":true},{\"color\":\"#fff\",\"id\":20,\"groupByGroup\":{\"name\":\"C\"},\"on\":true},{\"color\":\"#fff\",\"id\":21,\"groupByGroup\":{\"name\":\"C\"},\"on\":true},{\"color\":\"#fff\",\"id\":22,\"groupByGroup\":{\"name\":\"C\"},\"on\":true},{\"color\":\"#fff\",\"id\":23,\"groupByGroup\":{\"name\":\"C\"},\"on\":true},{\"color\":\"#fff\",\"id\":24,\"groupByGroup\":{\"name\":\"C\"},\"on\":true},{\"color\":\"#fff\",\"id\":25,\"groupByGroup\":{\"name\":\"C\"},\"on\":true},{\"color\":\"#fff\",\"id\":26,\"groupByGroup\":{\"name\":\"C\"},\"on\":true},{\"color\":\"#fff\",\"id\":27,\"groupByGroup\":{\"name\":\"C\"},\"on\":true},{\"color\":\"#fff\",\"id\":28,\"groupByGroup\":{\"name\":\"D\"},\"on\":true},{\"color\":\"#fff\",\"id\":29,\"groupByGroup\":{\"name\":\"D\"},\"on\":true},{\"color\":\"#fff\",\"id\":30,\"groupByGroup\":{\"name\":\"D\"},\"on\":true},{\"color\":\"#fff\",\"id\":31,\"groupByGroup\":{\"name\":\"D\"},\"on\":true},{\"color\":\"#fff\",\"id\":32,\"groupByGroup\":{\"name\":\"D\"},\"on\":true},{\"color\":\"#fff\",\"id\":33,\"groupByGroup\":{\"name\":\"D\"},\"on\":true},{\"color\":\"#fff\",\"id\":34,\"groupByGroup\":{\"name\":\"D\"},\"on\":true},{\"color\":\"#fff\",\"id\":35,\"groupByGroup\":{\"name\":\"D\"},\"on\":true},{\"color\":\"#fff\",\"id\":36,\"groupByGroup\":{\"name\":\"E\"},\"on\":true},{\"color\":\"#fff\",\"id\":37,\"groupByGroup\":{\"name\":\"E\"},\"on\":true},{\"color\":\"#fff\",\"id\":38,\"groupByGroup\":{\"name\":\"E\"},\"on\":true},{\"color\":\"#fff\",\"id\":39,\"groupByGroup\":{\"name\":\"E\"},\"on\":true},{\"color\":\"#fff\",\"id\":40,\"groupByGroup\":{\"name\":\"E\"},\"on\":true},{\"color\":\"#fff\",\"id\":41,\"groupByGroup\":{\"name\":\"E\"},\"on\":true},{\"color\":\"#fff\",\"id\":42,\"groupByGroup\":{\"name\":\"E\"},\"on\":true},{\"color\":\"#fff\",\"id\":43,\"groupByGroup\":{\"name\":\"E\"},\"on\":true},{\"color\":\"#ffd000\",\"id\":1,\"groupByGroup\":{\"name\":\"F\"},\"on\":true},{\"color\":\"#ffd000\",\"id\":5,\"groupByGroup\":{\"name\":\"F\"},\"on\":true},{\"color\":\"#ffd000\",\"id\":6,\"groupByGroup\":{\"name\":\"F\"},\"on\":true},{\"color\":\"#ffd0ff\",\"id\":7,\"groupByGroup\":{\"name\":\"F\"},\"on\":true},{\"color\":\"#ffd0ff\",\"id\":8,\"groupByGroup\":{\"name\":\"F\"},\"on\":true},{\"color\":\"#ffd0ff\",\"id\":9,\"groupByGroup\":{\"name\":\"F\"},\"on\":true},{\"color\":\"#ffd0ff\",\"id\":3,\"groupByGroup\":{\"name\":\"F\"},\"on\":true},{\"color\":\"#fff\",\"id\":4,\"groupByGroup\":{\"name\":\"F\"},\"on\":false},{\"color\":\"#cde\",\"id\":2,\"groupByGroup\":{\"name\":\"B\"},\"on\":true},{\"color\":\"#0f0\",\"id\":10,\"groupByGroup\":{\"name\":\"B\"},\"on\":false},{\"color\":\"#000\",\"id\":46,\"groupByGroup\":{\"name\":\"G\"},\"on\":false},{\"color\":\"#000\",\"id\":47,\"groupByGroup\":{\"name\":\"G\"},\"on\":false},{\"color\":\"#000\",\"id\":48,\"groupByGroup\":{\"name\":\"G\"},\"on\":false},{\"color\":\"#000\",\"id\":49,\"groupByGroup\":{\"name\":\"G\"},\"on\":false},{\"color\":\"#000\",\"id\":50,\"groupByGroup\":{\"name\":\"G\"},\"on\":false},{\"color\":\"#000\",\"id\":51,\"groupByGroup\":{\"name\":\"G\"},\"on\":false},{\"color\":\"#000\",\"id\":52,\"groupByGroup\":{\"name\":\"G\"},\"on\":false},{\"color\":\"#000\",\"id\":53,\"groupByGroup\":{\"name\":\"G\"},\"on\":false},{\"color\":\"#000\",\"id\":54,\"groupByGroup\":{\"name\":\"H\"},\"on\":false},{\"color\":\"#000\",\"id\":55,\"groupByGroup\":{\"name\":\"H\"},\"on\":false},{\"color\":\"#000\",\"id\":56,\"groupByGroup\":{\"name\":\"H\"},\"on\":false}]}"));

        leds.getGroupLeds();
        verify(apiService).getLights();
        verifyNoMoreInteractions(apiService);
    }
}
