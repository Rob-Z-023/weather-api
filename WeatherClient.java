import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherClient {
    private final String APPID;
    private HttpClient Client;
    private static final String BASE_URL = "http://api.openweathermap.org/geo/1.0/direct?q=";

    public WeatherClient(String appid) {
        this.APPID = appid;
        Client = HttpClient.newHttpClient();
    }

    // http get handler
    private HttpResponse<String> getCoordinates(URI uri) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET().build();
        HttpResponse<String> response = null;
        try{
            response = Client.send(request, HttpResponse.BodyHandlers.ofString());
        }
        catch(IOException | InterruptedException e){
            System.out.println("Coordinates connection failed.");
            e.printStackTrace();
        }
        return response;
    }

    public HttpResponse<String> getCoordinates(String location){
        String uri = BASE_URL + location + "&appid=" + APPID;
        return getCoordinates(URI.create(uri));
    }

//    // it is more likely for two country to have the same city name, therefore this overload is chosen over
//    // the state code overload
//    HttpResponse<String> getCoordinates(String location, int countryCode){
//        String uri = BASE_URL + location + "," + countryCode + "&appid=" + APPID;
//        return getCoordinates(URI.create(uri));
//    }
//
//    HttpResponse<String> getCoordinates(String location, int countryCode, int stateCode){
//        String uri = BASE_URL + location + "," + stateCode + "," + countryCode + "&appid=" + APPID;
//        return getCoordinates(URI.create(uri));
//    }
}
