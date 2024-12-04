import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Scanner;

public class WeatherApp {
    private final WeatherClient weatherClient;
    private final Scanner scanner;

    public WeatherApp(String appid) {
        weatherClient = new WeatherClient(appid);
        scanner = new Scanner(System.in);
    }

    // get the coordinates of the first search result of the API query
    public Coordinates getCoordinates(String city) {
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

    public int inputHandler(){
        String[] input = scanner.nextLine().toLowerCase().trim().split(" ");

        if(input.length < 1) return 1;

        switch(input[0]){
            case "help":
                help();
                break;

            case "get":
                if(input.length < 2) inputError();
                else System.out.println(getWeather(rebuild(input, 1)));
                break;

            case "getcord":
                if(input.length < 2) inputError();
                else System.out.println(getCoordinates(rebuild(input, 1)));
                break;

//            case "favorite":
//                if(input.length < 2) inputError();
//                switch (input[1]){
//                    case "add":
//                        if(input.length < 3) inputError();
//                    case "remove":
//                    case "list":
//                    default:
//                        inputError();
//                }

            case "exit":
                return 0;
            default:
                inputError();
        }
        return 1;
    }

    private void help(){
        System.out.println("Get Weather Data\n" +
                " - get [City Name]\n\n" +
                "Get City Cooridinate\n" +
                " - getcord [City Name]\n\n" +
                "Adds, removes, and lists your favorite cities\n" +
                " - favorite (add/remove/list) [City Name]\n\n" +
                "Exits program and saves favorites\n" +
                " - exit\n\n");
    }

    private void inputError(){
        System.out.println("Invalid input");
    }

    private String rebuild(String[] input, int start){
        StringBuilder output = new StringBuilder();
        for(int i = start; i < input.length; i++){
            output.append(input[i]);
            output.append("%20");
        }
        return output.toString();
    }


    public static void main(String[] args){
        String APPID = "32c569e2c7f82c2d939553d3b7841a26";
        WeatherApp app = new WeatherApp(APPID);

        while(app.inputHandler() != 0);
    }
}
