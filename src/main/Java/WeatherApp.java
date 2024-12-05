import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class WeatherApp {
    private final WeatherClient weatherClient;
    private final Scanner scanner;
    private final int favoriteLen = 3;
    private final Set<String> favorites;

    public WeatherApp(String appid) {
        weatherClient = new WeatherClient(appid);
        scanner = new Scanner(System.in);
        favorites = new HashSet<>(favoriteLen);
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

    public void addFavorite(String city) {
        if(favorites.contains(city)) {
            System.out.println("City already in favorite");
            return;
        }
        if(favorites.size() >= favoriteLen) {
            System.out.println("Your favorites list is full, please remove a city from the list.");
            return;
        }
        favorites.add(city);
        System.out.println("City successfully added to favorite");
    }

    public void removeFavorite(String city) {
        if(!favorites.contains(city)) {
            System.out.println("City not in favorite");
            return;
        }
        favorites.remove(city);
        System.out.println("City successfully removed from favorite");
    }

    public String listFavorites() {
        StringBuilder output = new StringBuilder();
        for(String city : favorites) {
            output.append(getWeather(city)).append("\n");
        }
        return output.toString();
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

            case "favorite":
                if(input.length < 2) inputError();
                switch (input[1]){
                    case "add":
                        if(input.length < 3) inputError();
                        addFavorite(rebuild(input, 2));
                        break;
                    case "remove":
                        if(input.length < 3) inputError();
                        removeFavorite(rebuild(input, 2));
                        break;
                    case "list":
                        System.out.println(listFavorites());
                        break;
                    default:
                        inputError();
                }
                break;

            case "exit":
                return 0;
            default:
                inputError();
        }
        return 1;
    }

    private void help(){
        System.out.println("""
                Get Weather Data
                 - get [City Name]
                
                Get City Cooridinate
                 - getcord [City Name]
                
                Adds, removes, and lists your favorite cities
                 - favorite (add/remove/list) [City Name]
                
                Exits program and saves favorites
                 - exit
                
                """);
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
