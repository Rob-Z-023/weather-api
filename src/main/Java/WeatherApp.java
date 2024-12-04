import org.json.JSONObject;

import java.net.http.HttpResponse;
import java.util.Scanner;

public class WeatherApp {


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String APPID = "32c569e2c7f82c2d939553d3b7841a26";
        WeatherClient client = new WeatherClient(APPID);

        while(true){
            System.out.print("Enter location: ");
            String location = sc.nextLine();
            if(location.toLowerCase().trim().equals("exit")) break;

            location = location.replace(" ", "%20");
            HttpResponse<String> response = client.getCoordinates(location);
            JSONObject cordResponse = new JSONObject(response.body());

            System.out.println(response.body());
        }

    }
}
