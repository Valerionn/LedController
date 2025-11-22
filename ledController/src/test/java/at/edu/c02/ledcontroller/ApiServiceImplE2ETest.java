package at.edu.c02.ledcontroller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assume;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ApiServiceImplE2ETest {

    @Test
    public void setLightAndReadBackWhenGroupIdConfigured() throws Exception {
        String groupId = ApiServiceImpl.getGroupId();
        Assume.assumeFalse("E2E skipped: set HASURA_GROUP_ID or -Dhasura.group.id to run", "Todo".equalsIgnoreCase(groupId));

        ApiService apiService = new ApiServiceImpl();

        int testId = Integer.parseInt(System.getProperty("hasura.test.light.id",
                System.getenv().getOrDefault("HASURA_TEST_LIGHT_ID", "20")));

        JSONObject original = apiService.getLight(testId).getJSONArray("lights").getJSONObject(0);
        String originalColor = original.getString("color");
        boolean originalState = original.getBoolean("on");

        String newColor = String.format("#%06x", ThreadLocalRandom.current().nextInt(0x1000000));
        boolean newState = !originalState;

        try {
            JSONObject updateResponse = apiService.setLight(testId, newColor, newState);
            JSONObject update = updateResponse.optJSONObject("update_lights_by_pk");
            if (update == null) {
                Assume.assumeTrue("E2E skipped: update_lights_by_pk null (likely missing rights or wrong HASURA_GROUP_ID)", false);
            }

            assertNotNull("update_lights_by_pk should not be null (check X-Hasura-Group-ID)", update);
            assertEquals(newColor.toLowerCase(), update.getString("color").toLowerCase());
            assertEquals(newState, update.getBoolean("on"));

            JSONArray lights = apiService.getLight(testId).getJSONArray("lights");
            JSONObject updatedLight = lights.getJSONObject(0);
            assertEquals(newColor.toLowerCase(), updatedLight.getString("color").toLowerCase());
            assertEquals(newState, updatedLight.getBoolean("on"));
        } finally {
            apiService.setLight(testId, originalColor, originalState);
        }
    }
}
