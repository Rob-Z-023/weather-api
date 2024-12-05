public class Coordinates{
    private final String latitude, longitude;

    public Coordinates(String latitude, String longitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }
    public String getLatitude(){
        return latitude;
    }

    public String getLongitude(){
        return longitude;
    }

    public String toString() {
        return "lat: " + latitude + "   lon: " + longitude;
    }
}