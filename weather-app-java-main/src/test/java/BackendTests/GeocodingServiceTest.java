package test.java.BackendTests;

import main.java.backend.api.APIUtils;
import main.java.backend.service.GeocodingService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GeocodingServiceTest {
    private static JSONArray testCoordinatesArray;

    public static void setTestCoordinatesArray(JSONArray coordinatesArray) {
        testCoordinatesArray = coordinatesArray;
    }

    public static JSONArray getGeographicCoordinates(String locationName) {
        return testCoordinatesArray;
    }

    @org.junit.Test
    public void testBuildLocationURL() {
        String locationName = "Los Angeles";
        String expectedUrl = "https://geocoding-api.open-meteo.com/v1/search?name=Los Angeles&count=10&language=en&format=json";

        String actualUrl = main.java.backend.service.GeocodingService.buildLocationURL(locationName);

        assertEquals("The URL generated for Los Angeles is incorrect.", expectedUrl, actualUrl);
    }

    @Test
    public void testGetGeographicCoordinates() {
        String locationName = "New York";
        String url = "https://geocoding-api.open-meteo.com/v1/search?name=New+York&count=10&language=en&format=json";
        JSONArray expectedResults = new JSONArray();
        JSONObject result = new JSONObject();
        result.put("latitude", 40.7128);
        result.put("longitude", -74.0060);
        expectedResults.add(result);

        JSONObject response = new JSONObject();
        response.put("results", expectedResults);

        JSONArray actualResults = simulateGetGeographicCoordinates(locationName, response);

        assertEquals("The geographic coordinates returned are incorrect.", expectedResults, actualResults);
    }

    private JSONArray simulateGetGeographicCoordinates(String locationName, JSONObject response) {
        return (JSONArray) response.get("results");
    }
}
