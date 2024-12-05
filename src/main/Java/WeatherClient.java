import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherClient {
    private final String APPID;
    private final HttpClient Client;
    private static final String BASE_CORD_URL = "http://api.openweathermap.org/geo/1.0/direct?q=";
    private static final String BASE_WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?";

    public WeatherClient(String appid) {
        this.APPID = appid;
        Client = HttpClient.newHttpClient();
    }

    // http get handler
    private HttpResponse<String> getHTML(URI uri) {
        // building html request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET().build();

        // sending request and receiving response
        HttpResponse<String> response = null;
        try{
            response = Client.send(request, HttpResponse.BodyHandlers.ofString());
        }
        catch(IOException | InterruptedException e){
            System.out.println("GET request failed.");
            e.printStackTrace();
        }
        return response;
    }

    // return the response body of the Coordinates get request
    public String getCoordinates(String location){
        String uri = BASE_CORD_URL + location + "&appid=" + APPID;
        return getHTML(URI.create(uri)).body();
    }

    // return the response body of the Weather get request
    public String getWeather(Coordinates coordinates){
        String uri = BASE_WEATHER_URL + "lat=" + coordinates.getLatitude() + "&lon=" + coordinates.getLongitude()
                + "&appid=" + APPID;

        return getHTML(URI.create(uri)).body();
    }
}
