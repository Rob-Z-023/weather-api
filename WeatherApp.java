import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class WeatherApp {


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String APPID = "32c569e2c7f82c2d939553d3b7841a26";
        WeatherClient client = new WeatherClient(APPID);

        HttpResponse<String> response = client.getCoordinates("London");

        System.out.println(response.body());
    }
}
