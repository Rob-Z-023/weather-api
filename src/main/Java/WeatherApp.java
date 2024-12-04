import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Scanner;

public class WeatherApp {
    private WeatherClient weatherClient;

    public WeatherApp(String appid) {
        weatherClient = new WeatherClient(appid);
    }

    public Coordinates getCoordinates(String city) {
        city = city.replace(" ", "%20");
        JSONObject cityObject = new JSONArray(weatherClient.getCoordinates(city)).getJSONObject(0);
        return new Coordinates(cityObject.get("lat").toString(), cityObject.get("lon").toString());
    }




    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String APPID = "32c569e2c7f82c2d939553d3b7841a26";
        WeatherApp app = new WeatherApp(APPID);

        while(true){
            System.out.print("Enter location: ");
            String location = sc.nextLine();
            if(location.toLowerCase().trim().equals("exit")) break;

            System.out.println(app.getCoordinates(location));
        }

    }
}
