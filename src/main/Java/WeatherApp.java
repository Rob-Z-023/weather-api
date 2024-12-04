import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Scanner;

public class WeatherApp {
    private WeatherClient weatherClient;

    public WeatherApp(String appid) {
        weatherClient = new WeatherClient(appid);
    }

    // get the coordinates of the first search result of the API query
    public Coordinates getCoordinates(String city) {
        city = city.replace(" ", "%20");
        JSONObject cityObject = new JSONArray(weatherClient.getCoordinates(city)).getJSONObject(0);
        return new Coordinates(cityObject.get("lat").toString(), cityObject.get("lon").toString());
    }

    // get the current weather data of the coordinate
    public String getWeather(Coordinates coordinates) {
        //JSONObject cityObject = new JSONObject(weatherClient.getWeather(coordinates));
        StringBuilder output = new StringBuilder();
        output.append(weatherClient.getWeather(coordinates));
        return output.toString();
    }

    // get the current weather data of the city specified based on getCoodinates method
    public String getWeather(String city) {
        return getWeather(getCoordinates(city));
    }


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String APPID = "32c569e2c7f82c2d939553d3b7841a26";
        WeatherApp app = new WeatherApp(APPID);

        while(true){
            System.out.print("Enter location: ");
            String location = sc.nextLine();
            if(location.toLowerCase().trim().equals("exit")) break;

            System.out.println(app.getWeather(location));
        }

    }
}
