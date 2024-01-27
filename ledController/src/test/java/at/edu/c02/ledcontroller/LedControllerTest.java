package at.edu.c02.ledcontroller;

import org.json.JSONObject;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
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
    public void testGetGroupLeds(){
       ApiService apiService = mock(ApiService.class);
       LedController ledController = new LedControllerImpl(apiService);

       //when(apiService.getLights()).thenReturn(new JSONObject())


        // when(cal.perfom(Operation.add)).thenReturn(3.0);



    }
}
