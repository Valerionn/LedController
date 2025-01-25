package at.edu.c02.ledcontroller;

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
    public void testGroupLeds() throws IOException {
        ApiService apiService = mock(ApiService.class);
        LedController ledController = new LedControllerImpl(apiService);

        ledController.getGroupLeds();
        verify(apiService).getLights();
    }


}
